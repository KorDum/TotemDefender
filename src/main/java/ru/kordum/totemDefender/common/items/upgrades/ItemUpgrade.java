package ru.kordum.totemDefender.common.items.upgrades;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import ru.kordum.totemDefender.common.config.ConfigUpgrade;
import ru.kordum.totemDefender.common.items.ItemBase;
import ru.kordum.totemDefender.common.utils.Formatter;

import java.util.List;

public class ItemUpgrade extends ItemBase {
    public static final int LEVEL_WOODEN = 1;
    public static final int LEVEL_IRON = 2;
    public static final int LEVEL_GOLD = 3;
    public static final int LEVEL_DIAMOND = 4;

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
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(itemStack, player, tooltip, advanced);
        if (advanced || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            if (attackSpeed != 0) {
                tooltip.add(EnumChatFormatting.BLUE + Formatter.getProp("prop.attackSpeed", attackSpeed, isModifiersInPercent()));
            }
            if (damage != 0) {
                tooltip.add(EnumChatFormatting.RED + Formatter.getProp("prop.damage", damage, isModifiersInPercent()));
            }
            if (radius != 0) {
                tooltip.add(EnumChatFormatting.GREEN + Formatter.getProp("prop.radius", radius, isModifiersInPercent()));
            }
        } else {
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("prop.holdMore"));
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
