package ru.kordum.totemDefender.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import ru.kordum.totemDefender.common.blocks.BlockTotem;
import ru.kordum.totemDefender.common.items.upgrades.ItemFilter;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;
import ru.kordum.totemDefender.common.items.upgrades.ItemModifierUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class TileEntityTotem extends TileEntity implements
    IInventory,
    ITickable
    /*, IUpdatePlayerListBox*/ {
    private static final String NBT_INVENTORY = "Inventory";
    private static final String NBT_INVENTORY_SLOT = "Slot";
    private static final String NBT_ATTACK_SPEED = "AttackSpeed";
    private static final String NBT_DAMAGE = "Damage";
    private static final String NBT_RADIUS = "Radius";
    private static final String NBT_FILTER = "Filter";
    private static final String NBT_MODE = "Mode";
    private static final String NBT_OWNER = "Owner";
    private static final String NBT_MODIFIER = "Modifier";

    protected float damage;
    protected float attackSpeed;
    protected int radius;

    private ItemStack[] inventory;
    private short filter;
    private byte mode;
    private short modifier;
    private long lastShoot;
    private UUID owner;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityTotem() {
        inventory = new ItemStack[
            getFilterSlotCount() +
                getUpgradeSlotCount() +
                1
            ];
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public void update() {
        if (!world.isRemote) {
            search();
        }
    }

    /*@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(pos, 1, nbt);
    }*/

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    public void calculateStats(BlockTotem block) {
        modifier = 0;
        attackSpeed = block.getAttackSpeed();
        damage = block.getDamage();
        radius = block.getRadius();
        int offset = 1 + getFilterSlotCount();
        int count = offset + getUpgradeSlotCount();

        for (int i = offset; i < count; i++) {
            ItemStack stack = inventory[i];
            if (stack == null) {
                continue;
            }

            ItemUpgrade item = (ItemUpgrade) stack.getItem();
            if (item.isModifiersInPercent()) {
                attackSpeed += block.getAttackSpeed() * item.getAttackSpeed() / 100;
                damage += block.getDamage() * item.getDamage() / 100;
                radius += Math.ceil(block.getRadius() * item.getRadius() / 100);
            } else {
                attackSpeed += item.getAttackSpeed();
                damage += item.getDamage();
                radius += item.getRadius();
            }

            if (item instanceof ItemModifierUpgrade) {
                modifier |= ((ItemModifierUpgrade) item).getModifier();
            }
        }

        if (attackSpeed < 0) {
            attackSpeed = 0.1f;
        }
        if (damage < 0) {
            damage = 0.5f;
        }
        if (radius < 0) {
            radius = 1;
        }
    }

    private void calculateStats() {
        calculateStats((BlockTotem) getBlockType());
    }

    private void updateFilter() {
        filter = 0;

        for (byte i = 1; i <= getFilterSlotCount(); i++) {
            ItemStack itemStack = inventory[i];
            if (itemStack == null) {
                continue;
            }

            ItemFilter item = (ItemFilter) itemStack.getItem();
            filter |= item.getMode();
        }
    }

    private void updateMode() {
        ItemStack stack = inventory[0];
        if (stack != null) {
            ItemMode item = (ItemMode) stack.getItem();
            mode = item.getMode();
        } else {
            mode = 0;
        }
    }

    private ArrayList<EntityLivingBase> getEntityList() {
        ArrayList<EntityLivingBase> list = new ArrayList<>();
        AxisAlignedBB axis = new AxisAlignedBB(
            pos.getX() - radius,
            pos.getY() - radius,
            pos.getZ() - radius,
            pos.getX() + radius,
            pos.getY() + radius,
            pos.getZ() + radius
        );

        if ((filter & ItemFilter.ENEMY) == ItemFilter.ENEMY) {
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
        }

        return list;
    }

    protected Entity search() {
        if (filter == 0 || mode == 0) {
            return null;
        }

        long time = new Date().getTime();
        if (lastShoot != 0 && time - lastShoot < 1000 / attackSpeed) {
            return null;
        }

        ArrayList<EntityLivingBase> list = getEntityList();
        if ((mode & ItemMode.PROJECTILE) == ItemMode.PROJECTILE) {
            projectileShot(list);
        } else if ((mode & ItemMode.AOE) == ItemMode.AOE) {
            aoeShot(list);
        }

        lastShoot = time;
        return null;
    }

    private void aoeShot(ArrayList<EntityLivingBase> list) {
        /*Vec3d totemVector = new Vec3d(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
        for (EntityLivingBase entity : list) {
            if (!isDamageable(entity)) {
                continue;
            }

            AxisAlignedBB boundingBox = entity.getEntityBoundingBox();
            Vec3d entityVector = new Vec3d(
                boundingBox.minX + (boundingBox.maxX - boundingBox.minX) / 2,
                boundingBox.minY + (boundingBox.maxY - boundingBox.minY) / 2,
                boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) / 2
            );

            MovingObjectPosition objectPosition = world.rayTraceBlocks(totemVector, entityVector, true);
            if (objectPosition != null && objectPosition.entityHit != entity) {
                continue;
            }

            attack(entity);
        }*/
    }

    private void projectileShot(ArrayList<EntityLivingBase> list) {
        /*for (EntityLivingBase entity : list) {
            if (!isDamageable(entity)) {
                continue;
            }

            Vec3d totemVector = new Vec3d(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
            AxisAlignedBB boundingBox = entity.getEntityBoundingBox();
            Vec3d entityVector = new Vec3d(
                boundingBox.minX + (boundingBox.maxX - boundingBox.minX) / 2,
                boundingBox.minY + (boundingBox.maxY - boundingBox.minY) / 2,
                boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) / 2
            );

            MovingObjectPosition objectPosition = world.rayTraceBlocks(totemVector, entityVector, true);
            if (objectPosition != null && objectPosition.entityHit != entity) {
                continue;
            }

            Vec3d vector = entityVector.subtract(totemVector).normalize();
            EntityProjectile projectile = new EntityProjectile(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
            projectile.setOwner(this);
            projectile.motionX = vector.xCoord;
            projectile.motionY = vector.yCoord;
            projectile.motionZ = vector.zCoord;

            if ((modifier & ItemModifierUpgrade.FIRE) == ItemModifierUpgrade.FIRE) {
                projectile.setFire((int) damage);
            }

            world.spawnEntity(projectile);
            world.playSoundAtEntity(projectile, "random.bow", 1, 1);
            break;
        }*/
    }

    private boolean isDamageable(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if ((filter & ItemFilter.PLAYER) == ItemFilter.PLAYER) {
                return !player.capabilities.isCreativeMode;
            } else {
                if ((filter & ItemFilter.SELF_PLAYER) == ItemFilter.SELF_PLAYER) {
                    return !player.capabilities.isCreativeMode &&
                        player.getUniqueID().equals(owner);
                } else if ((filter & ItemFilter.ANOTHER_PLAYER) == ItemFilter.ANOTHER_PLAYER) {
                    return !player.capabilities.isCreativeMode &&
                        !player.getUniqueID().equals(owner);
                }
            }
        }
        return !entity.isDead;
    }

    public void attack(EntityLivingBase entity) {
        /*boolean needDamage = false;

        if ((modifier & ItemModifierUpgrade.FIRE) == ItemModifierUpgrade.FIRE) {
            entity.setFire((int) damage);
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.POISON) == ItemModifierUpgrade.POISON) {
            entity.addPotionEffect(new PotionEffect(Potion.poison.id, 200, 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.LIGHTING) == ItemModifierUpgrade.LIGHTING) {
            EntityLightningBolt lighting = new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ);
            world.addWeatherEffect(lighting);
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WITHER) == ItemModifierUpgrade.WITHER) {
            entity.addPotionEffect(new PotionEffect(Potion.wither.id, 60, 4));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.SLOWDOWN) == ItemModifierUpgrade.SLOWDOWN) {
            entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WATER_BREATHING) == ItemModifierUpgrade.WATER_BREATHING) {
            entity.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, (int) (300 * damage), 1));
        }

        if ((modifier & ItemModifierUpgrade.REGENERATION) == ItemModifierUpgrade.REGENERATION) {
            entity.addPotionEffect(new PotionEffect(Potion.regeneration.id, (int) (10 * damage), 1));
        }

        if ((modifier & ItemModifierUpgrade.BLINDNESS) == ItemModifierUpgrade.BLINDNESS) {
            entity.addPotionEffect(new PotionEffect(Potion.blindness.id, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WEAKNESS) == ItemModifierUpgrade.WEAKNESS) {
            entity.addPotionEffect(new PotionEffect(Potion.weakness.id, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.HUNGRY) == ItemModifierUpgrade.HUNGRY) {
            entity.addPotionEffect(new PotionEffect(Potion.hunger.id, (int) (60 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.CONFUSION) == ItemModifierUpgrade.CONFUSION) {
            entity.addPotionEffect(new PotionEffect(Potion.confusion.id, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.HEAL) == ItemModifierUpgrade.HEAL) {
            entity.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 1));
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
        }

        if (needDamage || modifier == 0) {
            entity.attackEntityFrom(new DamageSource("totem"), damage);
        }*/
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        /*NBTTagList tagList = nbt.getTagList(NBT_INVENTORY, 10);

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte(NBT_INVENTORY_SLOT);

            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }*/

        attackSpeed = nbt.getFloat(NBT_ATTACK_SPEED);
        damage = nbt.getFloat(NBT_DAMAGE);
        radius = nbt.getInteger(NBT_RADIUS);
        filter = nbt.getShort(NBT_FILTER);
        mode = nbt.getByte(NBT_MODE);
        modifier = nbt.getShort(NBT_MODIFIER);

        String ownerUuid = nbt.getString(NBT_OWNER);
        if (ownerUuid != null && !ownerUuid.isEmpty()) {
            owner = UUID.fromString(ownerUuid);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);
        /*NBTTagList itemList = new NBTTagList();

        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];

            if (stack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte(NBT_INVENTORY_SLOT, (byte) i);
                stack.writeToNBT(nbt);
                itemList.appendTag(nbt);
            }
        }

        nbt.setTag(NBT_INVENTORY, itemList);*/
        nbt.setFloat(NBT_ATTACK_SPEED, attackSpeed);
        nbt.setFloat(NBT_DAMAGE, damage);
        nbt.setFloat(NBT_RADIUS, radius);
        nbt.setShort(NBT_FILTER, filter);
        nbt.setByte(NBT_MODE, mode);
        nbt.setShort(NBT_MODIFIER, modifier);

        if (owner != null) {
            nbt.setString(NBT_OWNER, owner.toString());
        }
        return nbt;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.getCount() <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.isEmpty()) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.getCount() > getInventoryStackLimit()) {
            itemStack.setCount(getInventoryStackLimit());
        }

        // TODO перенести калькуляции в нормальное место
        calculateStats();
        updateFilter();
        updateMode();
    }

    /*@Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return world.getTileEntity(pos) == this &&
            player.getDistanceSq(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) < 64;
    }*/

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    public abstract int getFilterSlotCount();

    public abstract int getUpgradeSlotCount();

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
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

    public boolean hasStackInSlot(int slot) {
        return inventory[slot] != null;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public int getLevel() {
        return ((BlockTotem) getBlockType()).getLevel();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}