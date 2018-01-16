package ru.kordum.totemDefender.item.upgrade;

import net.minecraft.item.Item;

public class ItemMode extends Item {
    public static final byte PROJECTILE = 1;
    public static final byte AOE = 2;

    private byte mode;

    public ItemMode(byte mode) {
        this.mode = mode;
    }

    public byte getMode() {
        return mode;
    }
}
