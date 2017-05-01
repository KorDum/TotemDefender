package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.material.Material;
import ru.kordum.totemDefender.TotemDefender;

public class BlockFence extends net.minecraft.block.BlockFence {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockFence() {
        super(Material.wood);
        name = "totemTreeFence";
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
}
