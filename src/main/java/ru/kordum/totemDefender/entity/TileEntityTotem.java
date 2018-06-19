package ru.kordum.totemDefender.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.item.ItemFilter;
import ru.kordum.totemDefender.item.ItemMode;
import ru.kordum.totemDefender.item.ItemUpgrade;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class TileEntityTotem extends TileEntity implements ICapabilityProvider, ITickable {
    private static final String NBT_ITEM_STACK_HANDLER = "ItemStackHandler";
    private static final String NBT_ATTACK_SPEED = "AttackSpeed";
    private static final String NBT_DAMAGE = "Damage";
    private static final String NBT_RADIUS = "Radius";
    private static final String NBT_FILTER = "Filter";
    private static final String NBT_MODE = "Mode";
    private static final String NBT_OWNER = "Owner";
    private static final String NBT_MODIFIER = "Modifier";

    private static final float MIN_ATTACK_SPEED = 0.1f;
    private static final float MIN_DAMAGE = 0.5f;
    private static final int MIN_RADIUS = 1;
    private static final int MAX_Z_DOWN_SCAN = 3;

    protected BlockTotem.EnumType type;
    protected ItemStackHandler handler;

    private float damage;
    private float attackSpeed;
    private int radius;

    private short filter;
    private byte mode;
    private short modifier;
    private long lastShoot;
    private UUID owner;

    public TileEntityTotem() {
        super();
        init();
        handler = new ItemStackHandler(type.getFilterSlots() + type.getUpgradeSlots() + 1);
        updateDefaultState();
    }

    protected abstract void init();

    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        if (filter == 0 || mode == 0) {
            return;
        }

        long time = new Date().getTime();
        if (lastShoot != 0 && time - lastShoot < 1000 / attackSpeed) {
            return;
        }

        ArrayList<EntityLivingBase> list = getEntityList();
        if (ItemMode.EnumType.PROJECTILE.check(mode)) {
            projectileShot(list);
        } else if (ItemMode.EnumType.AOE.check(mode)) {
            aoeShot(list);
        }

        lastShoot = time;
    }

    public void updateState() {
        updateDefaultState();
        modifier = 0;
        int offset = 1 + type.getFilterSlots();
        int count = offset + type.getUpgradeSlots();

        for (int i = offset; i < count; i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            ItemUpgrade.EnumType type = ItemUpgrade.EnumType.byMeta(stack.getMetadata());
            modifier |= type.getFlag();

            if (type.isAttackSpeedPercent()) {
                attackSpeed += type.getAttackSpeed() * type.getAttackSpeed() / 100;
            } else {
                attackSpeed += type.getAttackSpeed();
            }

            if (type.isDamagePercent()) {
                damage += type.getDamage() * type.getDamage() / 100;
            } else {
                damage += type.getDamage();
            }

            if (type.isRadiusPercent()) {
                radius += Math.ceil(type.getRadius() * type.getRadius() / 100);
            } else {
                radius += type.getRadius();
            }
        }

        if (attackSpeed < MIN_ATTACK_SPEED) {
            attackSpeed = MIN_ATTACK_SPEED;
        }
        if (damage < MIN_DAMAGE) {
            damage = MIN_DAMAGE;
        }
        if (radius < MIN_RADIUS) {
            radius = MIN_RADIUS;
        }
        markDirty();
    }

    public void updateFilter() {
        filter = 0;
        for (byte i = 1; i <= type.getFilterSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                ItemFilter.EnumType type = ItemFilter.EnumType.byMeta(stack.getMetadata());
                filter |= type.getFlag();
            }
        }
        markDirty();
    }

    public void updateMode() {
        ItemStack stack = handler.getStackInSlot(0);
        if (stack.isEmpty()) {
            mode = 0;
        } else {
            ItemMode.EnumType type = ItemMode.EnumType.byMeta(stack.getMetadata());
            mode = type.getFlag();
        }
        markDirty();
    }

    private ArrayList<EntityLivingBase> getEntityList() {
        ArrayList<EntityLivingBase> list = new ArrayList<>();
        AxisAlignedBB axis = new AxisAlignedBB(
            pos.getX() - radius,
            pos.getY() - radius,
            pos.getZ() - (radius > MAX_Z_DOWN_SCAN ? MAX_Z_DOWN_SCAN : radius),
            pos.getX() + radius,
            pos.getY() + radius,
            pos.getZ() + radius
        );

        if (ItemFilter.EnumType.ENEMY.check(filter)) {
            list.addAll(world.getEntitiesWithinAABB(EntityMob.class, axis));
        }

        if (ItemFilter.EnumType.PLAYER.check(filter) ||
            ItemFilter.EnumType.SELF_PLAYER.check(filter) ||
            ItemFilter.EnumType.ANOTHER_PLAYER.check(filter)) {
            list.addAll(world.getEntitiesWithinAABB(EntityPlayer.class, axis));
        }

        if (ItemFilter.EnumType.ANIMAL.check(filter)) {
            list.addAll(world.getEntitiesWithinAABB(EntityAnimal.class, axis));
        }

        if (ItemFilter.EnumType.SLIME.check(filter)) {
            list.addAll(world.getEntitiesWithinAABB(EntitySlime.class, axis));
        }

        if (ItemFilter.EnumType.WATER.check(filter)) {
            list.addAll(world.getEntitiesWithinAABB(EntityWaterMob.class, axis));
        }
        return list;
    }

    private void aoeShot(ArrayList<EntityLivingBase> list) {
        Vec3d totemVector = new Vec3d(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
        for (EntityLivingBase entity : list) {
            if (isNotDamageable(entity)) {
                continue;
            }

            AxisAlignedBB boundingBox = entity.getEntityBoundingBox();
            Vec3d entityVector = new Vec3d(
                boundingBox.minX + (boundingBox.maxX - boundingBox.minX) / 2,
                boundingBox.minY + (boundingBox.maxY - boundingBox.minY) / 2,
                boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) / 2
            );

            RayTraceResult objectPosition = world.rayTraceBlocks(totemVector, entityVector, true);
            if (objectPosition == null || objectPosition.entityHit == entity) {
                attack(entity);
            }
        }
    }

    private void projectileShot(ArrayList<EntityLivingBase> list) {
        for (EntityLivingBase entity : list) {
            if (isNotDamageable(entity)) {
                continue;
            }

            Vec3d totemVector = new Vec3d(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
            AxisAlignedBB boundingBox = entity.getEntityBoundingBox();
            Vec3d entityVector = new Vec3d(boundingBox.minX + (boundingBox.maxX - boundingBox.minX) / 2, boundingBox.minY + (boundingBox.maxY - boundingBox.minY) / 2, boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) / 2);

            RayTraceResult objectPosition = world.rayTraceBlocks(totemVector, entityVector, true);
            if (objectPosition != null && objectPosition.entityHit != entity) {
                continue;
            }

            Vec3d vector = entityVector.subtract(totemVector).normalize();
            EntityProjectile projectile = new EntityProjectile(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
            projectile.setOwner(this);
            projectile.motionX = vector.x;
            projectile.motionY = vector.y;
            projectile.motionZ = vector.z;

            if (ItemUpgrade.EnumType.FIRE.check(modifier)) {
                projectile.setFire((int) damage);
            }

            world.spawnEntity(projectile);
            world.playSound(null, pos, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1.0F, 1.0F);
            break;
        }
    }

    private boolean isNotDamageable(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (ItemFilter.EnumType.PLAYER.check(filter)) {
                return player.capabilities.isCreativeMode;
            }

            if (ItemFilter.EnumType.SELF_PLAYER.check(filter)) {
                return player.capabilities.isCreativeMode || !player.getUniqueID().equals(owner);
            } else if (ItemFilter.EnumType.ANOTHER_PLAYER.check(filter)) {
                return player.capabilities.isCreativeMode || player.getUniqueID().equals(owner);
            }
        }
        return entity.isDead;
    }

    public void attack(EntityLivingBase entity) {
        boolean needDamage = true;
        if (ItemUpgrade.EnumType.FIRE.check(modifier)) {
            entity.setFire((int) damage);
        }

        if (ItemUpgrade.EnumType.POISON.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1));
        }

        if (ItemUpgrade.EnumType.LIGHTING.check(modifier)) {
            EntityLightningBolt lighting = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, false);
            world.addWeatherEffect(lighting);
        }

        if (ItemUpgrade.EnumType.WITHER.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 4));
        }

        if (ItemUpgrade.EnumType.SLOWDOWN.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1));
        }

        if (ItemUpgrade.EnumType.WATER_BREATHING.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, (int) (300 * damage), 1));
            needDamage = false;
        }

        if (ItemUpgrade.EnumType.REGENERATION.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (int) (10 * damage), 1));
            needDamage = false;
        }

        if (ItemUpgrade.EnumType.BLINDNESS.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (int) (30 * damage), 1));
        }

        if (ItemUpgrade.EnumType.WEAKNESS.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (30 * damage), 1));
        }

        if (ItemUpgrade.EnumType.HUNGRY.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (60 * damage), 1));
        }

        if (ItemUpgrade.EnumType.CONFUSION.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, (int) (30 * damage), 1));
        }

        if (ItemUpgrade.EnumType.HEAL.check(modifier)) {
            entity.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
            needDamage = false;
        }

        if (ItemUpgrade.EnumType.KNOCKBACK.check(modifier)) {
            BlockPos pos = getPos();
            double dx = pos.getX() - entity.posX;
            double dy = pos.getY() - entity.posY;
            double dz = pos.getZ() - entity.posZ;
            double strength = 0.5 / (dx * dx + dy * dy + dz * dz) * damage;
            if (strength > 1) {
                strength = 1;
            }
            entity.addVelocity(
                ((dx > 0) ? -1 : 1) * strength,
                ((dy > 0) ? -1 : 1) * strength,
                ((dz > 0) ? -1 : 1) * strength
            );
        }

        if (needDamage || modifier == 0) {
            entity.attackEntityFrom(new DamageSource("totem"), damage);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        handler.deserializeNBT(nbt.getCompoundTag(NBT_ITEM_STACK_HANDLER));
        attackSpeed = nbt.getFloat(NBT_ATTACK_SPEED);
        damage = nbt.getFloat(NBT_DAMAGE);
        radius = nbt.getInteger(NBT_RADIUS);
        filter = nbt.getShort(NBT_FILTER);
        mode = nbt.getByte(NBT_MODE);
        modifier = nbt.getShort(NBT_MODIFIER);

        String ownerUuid = nbt.getString(NBT_OWNER);
        if (!ownerUuid.isEmpty()) {
            owner = UUID.fromString(ownerUuid);
        }
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag(NBT_ITEM_STACK_HANDLER, handler.serializeNBT());
        nbt.setFloat(NBT_ATTACK_SPEED, attackSpeed);
        nbt.setFloat(NBT_DAMAGE, damage);
        nbt.setFloat(NBT_RADIUS, radius);
        nbt.setShort(NBT_FILTER, filter);
        nbt.setByte(NBT_MODE, mode);
        nbt.setShort(NBT_MODIFIER, modifier);

        if (owner != null) {
            nbt.setString(NBT_OWNER, owner.toString());
        }
        return super.writeToNBT(nbt);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new SPacketUpdateTileEntity(pos, getBlockMetadata(), nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        readFromNBT(tag);
    }

    @Nonnull
    @Override
    public NBTTagCompound getTileData() {
        return getUpdateTag();
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) handler;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(getPos()) == this
            && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
    }

    protected void updateDefaultState() {
        attackSpeed = type.getAttackSpeed();
        damage = type.getDamage();
        radius = type.getRadius();
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getDamage() {
        return damage;
    }

    public int getRadius() {
        return radius;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public BlockTotem.EnumType getType() {
        return type;
    }
}
