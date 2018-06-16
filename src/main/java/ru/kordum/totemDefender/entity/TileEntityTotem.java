package ru.kordum.totemDefender.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.item.ItemFilter;
import ru.kordum.totemDefender.item.ItemUpgrade;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class TileEntityTotem extends TileEntity implements ICapabilityProvider, ITickable {
    private static final String NBT_ITEM_STACK_HANDLER = "ItemStackHandler";
    private static final String NBT_ATTACK_SPEED = "AttackSpeed";
    private static final String NBT_DAMAGE = "Damage";
    private static final String NBT_RADIUS = "Radius";
    private static final String NBT_FILTER = "Filter";
    private static final String NBT_MODE = "Mode";
    private static final String NBT_OWNER = "Owner";
    private static final String NBT_MODIFIER = "Modifier";

    private float damage;
    private float attackSpeed;
    private int radius;

    private BlockTotem.EnumType type;
    private ItemStackHandler handler;
    private short filter;
    private byte mode;
    private short modifier;
    private long lastShoot;
    private UUID owner;

    public TileEntityTotem(BlockTotem.EnumType type) {
        this.type = type;
        handler = new ItemStackHandler(type.getFilterSlots() + type.getUpgradeSlots() + 1);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            search();
        }
    }

    public void updateState(BlockTotem block) {
        modifier = 0;
//        attackSpeed = block.getAttackSpeed();
//        damage = block.getDamage();
//        radius = block.getRadius();
        int offset = 1 + type.getFilterSlots();
        int count = offset + type.getUpgradeSlots();

        for (int i = offset; i < count; i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            ItemUpgrade item = (ItemUpgrade) stack.getItem();
            /*if (item.isAttackSpeedPercent()) {
                attackSpeed += block.getAttackSpeed() * item.getAttackSpeed() / 100;
            } else {
                attackSpeed += item.getAttackSpeed();
            }

            if (item.isDamagePercent()) {
                damage += block.getDamage() * item.getDamage() / 100;
            } else {
                damage += item.getDamage();
            }

            if (item.isRadiusPercent()) {
                radius += Math.ceil(block.getRadius() * item.getRadius() / 100);
            } else {
                radius += item.getRadius();
            }

            if (item instanceof ItemModifierUpgrade) {
                modifier |= ((ItemModifierUpgrade) item).getModifier();
            }*/
        }

        if (attackSpeed < 0.1f) {
            attackSpeed = 0.1f;
        }
        if (damage < 0.5f) {
            damage = 0.5f;
        }
        if (radius < 1) {
            radius = 1;
        }
    }

    public void updateState() {
        updateState((BlockTotem) getBlockType());
    }

    public void updateFilter() {
        filter = 0;
        for (byte i = 1; i <= type.getFilterSlots(); i++) {
            ItemStack stack = handler.getStackInSlot(i);
            if (stack.isEmpty()) {
                continue;
            }

            ItemFilter item = (ItemFilter) stack.getItem();
//            filter |= item.getMode();
        }
    }

    public void updateMode() {
        /*ItemStack stack = handler.getStackInSlot(0);
        if (!stack.isEmpty()) {
            ItemMode item = (ItemMode) stack.getItem();
            mode = item.getMode();
        } else {
            mode = 0;
        }*/
    }

    private ArrayList<EntityLivingBase> getEntityList() {
        ArrayList<EntityLivingBase> list = new ArrayList<>();
        AxisAlignedBB axis = new AxisAlignedBB(pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius, pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius);

        /*if ((filter & ItemFilter.ENEMY) == ItemFilter.ENEMY) {
            list.addAll(world.getEntitiesWithinAABB(EntityMob.class, axis));
        }

        if ((filter & ItemFilter.PLAYER) == ItemFilter.PLAYER ||
            (filter & ItemFilter.SELF_PLAYER) == ItemFilter.SELF_PLAYER ||
            (filter & ItemFilter.ANOTHER_PLAYER) == ItemFilter.ANOTHER_PLAYER) {
            list.addAll(world.getEntitiesWithinAABB(EntityPlayer.class, axis));
        }

        if ((filter & ItemFilter.ANIMAL) == ItemFilter.ANIMAL) {
            list.addAll(world.getEntitiesWithinAABB(EntityAnimal.class, axis));
        }

        if ((filter & ItemFilter.SLIME) == ItemFilter.SLIME) {
            list.addAll(world.getEntitiesWithinAABB(EntitySlime.class, axis));
        }

        if ((filter & ItemFilter.WATER_MOB) == ItemFilter.WATER_MOB) {
            list.addAll(world.getEntitiesWithinAABB(EntityWaterMob.class, axis));
        }*/
        return list;
    }

    protected void search() {
        if (filter == 0 || mode == 0) {
            return;
        }

        long time = new Date().getTime();
        if (lastShoot != 0 && time - lastShoot < 1000 / attackSpeed) {
            return;
        }

        ArrayList<EntityLivingBase> list = getEntityList();
        /*if ((mode & ItemMode.PROJECTILE) == ItemMode.PROJECTILE) {
            projectileShot(list);
        } else if ((mode & ItemMode.AOE) == ItemMode.AOE) {
            aoeShot(list);
        }*/

        lastShoot = time;
    }

    private void aoeShot(ArrayList<EntityLivingBase> list) {
        Vec3d totemVector = new Vec3d(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
        for (EntityLivingBase entity : list) {
            if (!isDamageable(entity)) {
                continue;
            }

            AxisAlignedBB boundingBox = entity.getEntityBoundingBox();
            Vec3d entityVector = new Vec3d(boundingBox.minX + (boundingBox.maxX - boundingBox.minX) / 2, boundingBox.minY + (boundingBox.maxY - boundingBox.minY) / 2, boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) / 2);

            RayTraceResult objectPosition = world.rayTraceBlocks(totemVector, entityVector, true);
            if (objectPosition != null && objectPosition.entityHit != entity) {
                continue;
            }

            attack(entity);
        }
    }

    private void projectileShot(ArrayList<EntityLivingBase> list) {
        for (EntityLivingBase entity : list) {
            if (!isDamageable(entity)) {
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

            /*if ((modifier & ItemModifierUpgrade.FIRE) == ItemModifierUpgrade.FIRE) {
                projectile.setFire((int) damage);
            }*/

            world.spawnEntity(projectile);
            world.playSound(null, pos, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1.0F, 1.0F);
            break;
        }
    }

    private boolean isDamageable(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            /*if ((filter & ItemFilter.PLAYER) == ItemFilter.PLAYER) {
                return !player.capabilities.isCreativeMode;
            }

            if ((filter & ItemFilter.SELF_PLAYER) == ItemFilter.SELF_PLAYER) {
                return !player.capabilities.isCreativeMode &&
                    player.getUniqueID().equals(owner);
            } else if ((filter & ItemFilter.ANOTHER_PLAYER) == ItemFilter.ANOTHER_PLAYER) {
                return !player.capabilities.isCreativeMode &&
                    !player.getUniqueID().equals(owner);
            }*/
        }
        return !entity.isDead;
    }

    public void attack(EntityLivingBase entity) {
        boolean needDamage = false;

        /*if ((modifier & ItemModifierUpgrade.FIRE) == ItemModifierUpgrade.FIRE) {
            entity.setFire((int) damage);
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.POISON) == ItemModifierUpgrade.POISON) {
            entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.LIGHTING) == ItemModifierUpgrade.LIGHTING) {
            EntityLightningBolt lighting = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, false);
            world.addWeatherEffect(lighting);
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WITHER) == ItemModifierUpgrade.WITHER) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60, 4));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.SLOWDOWN) == ItemModifierUpgrade.SLOWDOWN) {
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WATER_BREATHING) == ItemModifierUpgrade.WATER_BREATHING) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, (int) (300 * damage), 1));
        }

        if ((modifier & ItemModifierUpgrade.REGENERATION) == ItemModifierUpgrade.REGENERATION) {
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (int) (10 * damage), 1));
        }

        if ((modifier & ItemModifierUpgrade.BLINDNESS) == ItemModifierUpgrade.BLINDNESS) {
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WEAKNESS) == ItemModifierUpgrade.WEAKNESS) {
            entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.HUNGRY) == ItemModifierUpgrade.HUNGRY) {
            entity.addPotionEffect(new PotionEffect(MobEffects.HUNGER, (int) (60 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.CONFUSION) == ItemModifierUpgrade.CONFUSION) {
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.HEAL) == ItemModifierUpgrade.HEAL) {
            entity.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
        }

        if ((modifier & ItemModifierUpgrade.KNOCKBACK) != 0) {
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
        }*/

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

    public boolean isUseableByPlayer(EntityPlayer player) {
        return world.getTileEntity(getPos()) == this && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;
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
