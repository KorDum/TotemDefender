package ru.kordum.totemDefender.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class BlockPlanks extends Block {
    public BlockPlanks() {
        super(Material.WOOD);
        setHardness(4);
    }

    public enum EnumType implements IStringSerializable {
        TOTEM("totem");

        private final String name;

        EnumType(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }
    }
}
