package ru.kordum.totemDefender.common.items.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import ru.kordum.totemDefender.common.config.ConfigUpgrade;
import ru.kordum.totemDefender.common.items.ItemBase;
import ru.kordum.totemDefender.common.utils.Formatter;

import java.util.List;

public abstract class ItemUpgrade extends ItemBase {
    private float damage;
    private float attackSpeed;
    private int radius;
    private boolean modifiersIsPercent;
    private int level;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemUpgrade(String name, int level, ConfigUpgrade config) {
        super(name);
        this.level = level;
        attackSpeed = config.getAttackSpeed();
        damage = config.getDamage();
        radius = config.getRadius();
        modifiersIsPercent = config.isPercent();
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        super.addInformation(itemStack, player, list, par4);

        if (attackSpeed != 0) {
            list.add(EnumChatFormatting.BLUE + Formatter.getProp("prop.attackSpeed", attackSpeed, isModifiersInPercent()));
        }

        if (damage != 0) {
            list.add(EnumChatFormatting.RED + Formatter.getProp("prop.damage", damage, isModifiersInPercent()));
        }

        if (radius != 0) {
            list.add(EnumChatFormatting.GREEN + Formatter.getProp("prop.radius", radius, isModifiersInPercent()));
        }
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isModifiersInPercent() {
        return modifiersIsPercent;
    }

    public int getLevel() {
        return level;
    }
}
