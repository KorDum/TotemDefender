package ru.kordum.totemDefender.item.upgrade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

import ru.kordum.totemDefender.config.ConfigUpgrade;
import ru.kordum.totemDefender.util.Formatter;

public class ItemUpgrade extends Item {
    private float damage;
    private float attackSpeed;
    private int radius;
    private boolean modifiersIsPercent;
    private int level;

    public ItemUpgrade(int level, ConfigUpgrade config) {
        this.level = level;
        attackSpeed = config.getAttackSpeed();
        damage = config.getDamage();
        radius = config.getRadius();
        modifiersIsPercent = config.isPercent();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (flagIn.isAdvanced() || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            if (attackSpeed != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.BLUE, "prop.attack_speed", attackSpeed, isModifiersInPercent()));
            }
            if (damage != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.RED, "prop.damage", damage, isModifiersInPercent()));
            }
            if (radius != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.GREEN, "prop.radius", radius, isModifiersInPercent()));
            }
        } else {
            tooltip.add(Formatter.getLocalize(TextFormatting.GRAY, "prop.hold_more"));
        }
    }

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
