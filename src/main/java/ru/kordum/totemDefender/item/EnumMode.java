package ru.kordum.totemDefender.item;

import net.minecraft.util.IStringSerializable;

public enum EnumMode implements IStringSerializable {
    PROJECTILE("projectile"),
    AOE("aoe");

    private final String name;

    EnumMode(String name) {
        this.name = name;
    }

    public static EnumMode byMeta(int meta) {
        for (EnumMode type : EnumMode.values()) {
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
