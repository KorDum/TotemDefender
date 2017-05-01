package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;
import ru.kordum.totemDefender.TotemDefender;

public class BlockPlanks extends Block {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockPlanks() {
        super(Material.wood);
        name = "totemTreePlanks";
        setUnlocalizedName(name);
        setHardness(4);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public enum EnumType implements IStringSerializable {
        TOTEM("totem");

        private final String name;

        EnumType(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }
    }
}
