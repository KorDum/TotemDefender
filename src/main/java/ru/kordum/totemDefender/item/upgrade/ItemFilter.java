package ru.kordum.totemDefender.item.upgrade;

import net.minecraft.item.Item;

public class ItemFilter extends Item {
    public static final short PLAYER = 1;
    public static final short ANIMAL = 2;
    public static final short ENEMY = 4;
    public static final short SLIME = 8;
    public static final short WATER_MOB = 16;
    public static final short SELF_PLAYER = 32;
    public static final short ANOTHER_PLAYER = 64;

    private int mode;

    public ItemFilter(short mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
