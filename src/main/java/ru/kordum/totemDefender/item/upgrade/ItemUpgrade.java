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
    private int level;
    private ConfigUpgrade config;

    public ItemUpgrade(int level, ConfigUpgrade config) {
        this.level = level;
        this.config = config;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (flagIn.isAdvanced() || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            if (getAttackSpeed() != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.BLUE, "prop.attack_speed", getAttackSpeed(), isAttackSpeedPercent()));
            }
            if (getDamage() != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.RED, "prop.damage", getDamage(), isDamagePercent()));
            }
            if (getRadius() != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.GREEN, "prop.radius", getRadius(), isRadiusPercent()));
            }
        } else {
            tooltip.add(Formatter.getLocalize(TextFormatting.GRAY, "prop.hold_more"));
        }
    }

    public float getDamage() {
        return config.getDamage();
    }

    public float getAttackSpeed() {
        return config.getAttackSpeed();
    }

    public int getRadius() {
        return config.getRadius();
    }

    public boolean isAttackSpeedPercent() {
        return config.isSpeedPercent();
    }

    public boolean isDamagePercent() {
        return config.isDamagePercent();
    }

    public boolean isRadiusPercent() {
        return config.isRadiusPercent();
    }

    public int getLevel() {
        return level;
    }
}
