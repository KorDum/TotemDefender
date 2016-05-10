package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
        setBlockName(name);
        setBlockTextureName(TotemDefender.MODID + ":" + name);
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
}
