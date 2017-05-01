package ru.kordum.totemDefender.common.items.upgrades;

import ru.kordum.totemDefender.common.items.ItemBase;

public class ItemFilter extends ItemBase {
    public static final short PLAYER = 1;
    public static final short ANIMAL = 2;
    public static final short ENEMY = 4;
    public static final short SLIME = 8;
    public static final short WATER_MOB = 16;
    public static final short SELF_PLAYER = 32;
    public static final short ANOTHER_PLAYER = 64;

    private int mode;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemFilter(String name, short mode) {
        super(name);
        this.mode = mode;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC ACCESSORS
    //
    //---------------------------------------------------------------------------

    public int getMode() {
        return mode;
    }
}
