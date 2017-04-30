package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.config.ConfigUpgrade;

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

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemModifierUpgrade(String name, short modifier, ConfigUpgrade config) {
        super(name, 1, config);
        this.modifier = modifier;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public short getModifier() {
        return modifier;
    }
}
