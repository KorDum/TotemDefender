package ru.kordum.totemDefender.item;

import net.minecraft.util.IStringSerializable;
import ru.kordum.totemDefender.config.ConfigUpgrade;

public enum EnumUpgrade implements IStringSerializable {
    WOODEN_AS("wooden_as", ItemUpgrade.LEVEL_1),
    WOODEN_DAMAGE("wooden_damage", ItemUpgrade.LEVEL_1),
    WOODEN_RADIUS("wooden_radius", ItemUpgrade.LEVEL_1),

    IRON_AS("iron_as", ItemUpgrade.LEVEL_2),
    IRON_DAMAGE("iron_damage", ItemUpgrade.LEVEL_2),
    IRON_RADIUS("iron_radius", ItemUpgrade.LEVEL_2),

    GOLDEN_AS("golden_as", ItemUpgrade.LEVEL_3),
    GOLDEN_DAMAGE("golden_damage", ItemUpgrade.LEVEL_3),
    GOLDEN_RADIUS("golden_radius", ItemUpgrade.LEVEL_3),

    DIAMOND_AS("diamond_as", ItemUpgrade.LEVEL_4),
    DIAMOND_DAMAGE("diamond_damage", ItemUpgrade.LEVEL_4),
    DIAMOND_RADIUS("diamond_radius", ItemUpgrade.LEVEL_4),

    POISON("poison", ItemUpgrade.LEVEL_1),
    FIRE("fire", ItemUpgrade.LEVEL_1),
    LIGHTING("lighting", ItemUpgrade.LEVEL_1),
    WITHER("wither", ItemUpgrade.LEVEL_1),
    SLOWDOWN("slowdown", ItemUpgrade.LEVEL_1),
    BLINDNESS("blindness", ItemUpgrade.LEVEL_1),
    CONFUSION("confusion", ItemUpgrade.LEVEL_1),
    HEAL("heal", ItemUpgrade.LEVEL_1),
    HUNGRY("hungry", ItemUpgrade.LEVEL_1),
    REGENERATION("regeneration", ItemUpgrade.LEVEL_1),
    WATER_BREATHING("water_breathing", ItemUpgrade.LEVEL_1),
    WEAKNESS("weakness", ItemUpgrade.LEVEL_1),
    KNOCKBACK("knockback", ItemUpgrade.LEVEL_1);

    private String name;
    private int level;
    private ConfigUpgrade config;

    EnumUpgrade(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static EnumUpgrade byMeta(int meta) {
        for (EnumUpgrade type : EnumUpgrade.values()) {
            if (type.ordinal() == meta) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public float getAttackSpeed() {
        return config.getAttackSpeed();
    }

    public float getDamage() {
        return config.getDamage();
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

    public void setConfig(ConfigUpgrade config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return name;
    }
}
