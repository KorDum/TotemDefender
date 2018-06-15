package ru.kordum.totemDefender.item;

import net.minecraft.util.IStringSerializable;

public enum EnumFilter implements IStringSerializable {
    PLAYER("player"),
    SELF_PLAYER("self_player"),
    ANOTHER_PLAYER("another_player"),
    ANIMAL("animal"),
    ENEMY("enemy"),
    SLIME("slime"),
    WATER("water");

    private final String name;

    EnumFilter(String name) {
        this.name = name;
    }

    public static EnumFilter byMeta(int meta) {
        for (EnumFilter type : EnumFilter.values()) {
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

    @Override
    public String toString() {
        return name;
    }
}
