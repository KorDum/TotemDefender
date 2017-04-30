package ru.kordum.totemDefender.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.blocks.BlockTotem;
import ru.kordum.totemDefender.common.items.upgrades.ItemFilter;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;
import ru.kordum.totemDefender.common.items.upgrades.ItemModifierUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class TileEntityTotem extends TileEntity implements IInventory {
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
        inventory = new ItemStack[getFilterSlotCount() + getUpgradeSlotCount() + 1];
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!worldObj.isRemote) {
            search();
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readFromNBT(packet.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, (short) yCoord, zCoord, 1, nbt);
    }

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
            ItemStack itemStack = inventory[i];
            if (itemStack == null) {
                continue;
            }

            Item tempItem = itemStack.getItem();
            if (!(tempItem instanceof ItemUpgrade)) {
                TotemDefender.logger.debug("TOTEMDEFENDER: " + tempItem.toString());
                continue;
            }

            ItemUpgrade item = (ItemUpgrade) itemStack.getItem();
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
        ItemStack itemStack = inventory[0];
        if (itemStack != null) {
            ItemMode item = (ItemMode) itemStack.getItem();
            mode = item.getMode();
        } else {
            mode = 0;
        }
    }

    private ArrayList<Object> getEntityList() {
        ArrayList<Object> list = new ArrayList<>();
        AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(
            xCoord - radius,
            yCoord - radius,
            zCoord - radius,
            xCoord + radius,
            yCoord + radius,
            zCoord + radius
        );

        if ((filter & ItemFilter.ENEMY) != 0) {
            list.addAll(worldObj.getEntitiesWithinAABB(EntityMob.class, axis));
        }

        if ((filter & ItemFilter.PLAYER) != 0 ||
            (filter & ItemFilter.SELF_PLAYER) != 0 ||
            (filter & ItemFilter.ANOTHER_PLAYER) != 0) {
            list.addAll(worldObj.getEntitiesWithinAABB(EntityPlayer.class, axis));
        }

        if ((filter & ItemFilter.ANIMAL) != 0) {
            list.addAll(worldObj.getEntitiesWithinAABB(EntityAnimal.class, axis));
        }

        if ((filter & ItemFilter.SLIME) != 0) {
            list.addAll(worldObj.getEntitiesWithinAABB(EntitySlime.class, axis));
        }

        if ((filter & ItemFilter.WATER_MOB) != 0) {
            list.addAll(worldObj.getEntitiesWithinAABB(EntityWaterMob.class, axis));
        }

        return list;
    }

    private void search() {
        if (filter == 0 || mode == 0) {
            return;
        }

        long time = new Date().getTime();
        if (lastShoot != 0 && time - lastShoot < 1000 / attackSpeed) {
            return;
        }

        ArrayList<Object> list = getEntityList();
        if ((mode & ItemMode.PROJECTILE) != 0) {
            projectileShot(list);
        } else if ((mode & ItemMode.AOE) != 0) {
            aoeShot(list);
        }

        lastShoot = time;
    }

    private void aoeShot(ArrayList<Object> list) {
        Vec3 totemVector = Vec3.createVectorHelper(xCoord + 0.5, yCoord + 1.5, zCoord + 0.5);
        for (Object object : list) {
            EntityLivingBase entity = (EntityLivingBase) object;
            if (!isDamageable(entity)) {
                continue;
            }

            AxisAlignedBB boundingBox = entity.boundingBox;
            Vec3 entityVector = Vec3.createVectorHelper(
                boundingBox.minX + (boundingBox.maxX - boundingBox.minX) / 2,
                boundingBox.minY + (boundingBox.maxY - boundingBox.minY) / 2,
                boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) / 2
            );

            MovingObjectPosition objectPosition = worldObj.rayTraceBlocks(totemVector, entityVector, true);
            if (objectPosition != null && objectPosition.entityHit != entity) {
                continue;
            }

            attack(entity);
        }
    }

    private void projectileShot(ArrayList<Object> list) {
        for (Object object : list) {
            Entity entity = (Entity) object;
            if (!isDamageable(entity)) {
                continue;
            }

            Vec3 totemVector = Vec3.createVectorHelper(xCoord + 0.5, yCoord + 1.5, zCoord + 0.5);
            AxisAlignedBB boundingBox = entity.boundingBox;
            Vec3 entityVector = Vec3.createVectorHelper(
                boundingBox.minX + (boundingBox.maxX - boundingBox.minX) / 2,
                boundingBox.minY + (boundingBox.maxY - boundingBox.minY) / 2,
                boundingBox.minZ + (boundingBox.maxZ - boundingBox.minZ) / 2
            );

            MovingObjectPosition objectPosition = worldObj.rayTraceBlocks(totemVector, entityVector, true);
            if (objectPosition != null && objectPosition.entityHit != entity) {
                continue;
            }

            Vec3 vector = totemVector.subtract(entityVector).normalize();
            EntityProjectile projectile = new EntityProjectile(worldObj, xCoord + 0.5, yCoord + 1.5, zCoord + 0.5);
            projectile.setOwner(this);
            projectile.motionX = vector.xCoord;
            projectile.motionY = vector.yCoord;
            projectile.motionZ = vector.zCoord;

            if ((modifier & ItemModifierUpgrade.FIRE) == ItemModifierUpgrade.FIRE) {
                projectile.setFire((int) damage);
            }

            worldObj.spawnEntityInWorld(projectile);
            worldObj.playSoundAtEntity(projectile, "random.bow", 1, 1);
            break;
        }
    }

    private boolean isDamageable(Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;

            if ((filter & ItemFilter.PLAYER) != 0) {
                return !player.capabilities.isCreativeMode;
            } else {
                if ((filter & ItemFilter.SELF_PLAYER) != 0) {
                    return !player.capabilities.isCreativeMode &&
                        player.getUniqueID().equals(owner);
                } else if ((filter & ItemFilter.ANOTHER_PLAYER) != 0) {
                    return !player.capabilities.isCreativeMode &&
                        !player.getUniqueID().equals(owner);
                }
            }
        }
        return !entity.isDead;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList tagList = tagCompound.getTagList(NBT_INVENTORY, 10);

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte(NBT_INVENTORY_SLOT);
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }

        attackSpeed = tagCompound.getFloat(NBT_ATTACK_SPEED);
        damage = tagCompound.getFloat(NBT_DAMAGE);
        radius = tagCompound.getInteger(NBT_RADIUS);
        filter = tagCompound.getShort(NBT_FILTER);
        mode = tagCompound.getByte(NBT_MODE);
        modifier = tagCompound.getShort(NBT_MODIFIER);

        String ownerUuid = tagCompound.getString(NBT_OWNER);
        if (ownerUuid != null && !ownerUuid.isEmpty()) {
            owner = UUID.fromString(ownerUuid);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        NBTTagList itemList = new NBTTagList();

        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];
            if (stack != null) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setByte(NBT_INVENTORY_SLOT, (byte) i);
                stack.writeToNBT(nbt);
                itemList.appendTag(nbt);
            }
        }

        tagCompound.setTag(NBT_INVENTORY, itemList);
        tagCompound.setFloat(NBT_ATTACK_SPEED, attackSpeed);
        tagCompound.setFloat(NBT_DAMAGE, damage);
        tagCompound.setFloat(NBT_RADIUS, radius);
        tagCompound.setShort(NBT_FILTER, filter);
        tagCompound.setByte(NBT_MODE, mode);
        tagCompound.setShort(NBT_MODIFIER, modifier);

        if (owner != null) {
            tagCompound.setString(NBT_OWNER, owner.toString());
        }
    }

    public void attack(EntityLivingBase entity) {
        boolean needDamage = false;

        if ((modifier & ItemModifierUpgrade.FIRE) != 0) {
            entity.setFire((int) damage);
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.POISON) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.poison.id, 200, 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.LIGHTING) != 0) {
            EntityLightningBolt lighting = new EntityLightningBolt(worldObj, entity.posX, entity.posY, entity.posZ);
            worldObj.addWeatherEffect(lighting);
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WITHER) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.wither.id, 60, 4));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.SLOWDOWN) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WATER_BREATHING) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, (int) (300 * damage), 1));
        }

        if ((modifier & ItemModifierUpgrade.REGENERATION) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.regeneration.id, (int) (10 * damage), 1));
        }

        if ((modifier & ItemModifierUpgrade.BLINDNESS) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.blindness.id, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.WEAKNESS) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.weakness.id, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.HUNGRY) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.hunger.id, (int) (60 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.CONFUSION) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.confusion.id, (int) (30 * damage), 1));
            needDamage = true;
        }

        if ((modifier & ItemModifierUpgrade.HEAL) != 0) {
            entity.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 1));
        }

        if ((modifier & ItemModifierUpgrade.KNOCKBACK) != 0) {
            double dx = xCoord - entity.posX;
            double dy = yCoord - entity.posY;
            double dz = zCoord - entity.posZ;
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
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack == null) {
            return null;
        }

        if (stack.stackSize <= amount) {
            setInventorySlotContents(slot, null);
        } else {
            stack = stack.splitStack(amount);
            if (stack.stackSize == 0) {
                setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
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
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }

        // TODO перенести калькуляции в нормальное место
        calculateStats();
        updateFilter();
        updateMode();
    }

    @Override
    public abstract String getInventoryName();

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
            player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openInventory() {
        // ignore
    }

    @Override
    public void closeInventory() {
        // ignore
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
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
}
