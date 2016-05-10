package ru.kordum.totemDefender.common.items.modes;

import ru.kordum.totemDefender.common.items.ItemBase;

public abstract class ItemMode extends ItemBase {
    public static final byte PROJECTILE = 1;
    public static final byte AOE = 2;

    private byte mode;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemMode(String name, byte mode) {
        super(name);
        this.mode = mode;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public byte getMode() {
        return mode;
    }
}
