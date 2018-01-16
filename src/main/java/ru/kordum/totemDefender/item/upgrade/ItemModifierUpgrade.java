package ru.kordum.totemDefender.item.upgrade;

import ru.kordum.totemDefender.config.ConfigUpgrade;

public class ItemModifierUpgrade extends ItemUpgrade {
    public static final short POISON = 1;
    public static final short FIRE = 2;
    public static final short LIGHTING = 4;
    public static final short WITHER = 8;
    public static final short SLOWDOWN = 16;
    public static final short BLINDNESS = 32;
    public static final short CONFUSION = 64;
    public static final short HEAL = 128;
    public static final short HUNGRY = 256;
    public static final short REGENERATION = 512;
    public static final short WATER_BREATHING = 1024;
    public static final short WEAKNESS = 2048;
    public static final short KNOCKBACK = 4096;

    private short modifier;

    public ItemModifierUpgrade(short modifier, ConfigUpgrade config) {
        super(1, config);
        this.modifier = modifier;
    }

    public short getModifier() {
        return modifier;
    }
}
