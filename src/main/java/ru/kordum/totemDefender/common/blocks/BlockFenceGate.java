package ru.kordum.totemDefender.common.blocks;

import ru.kordum.totemDefender.TotemDefender;

public class BlockFenceGate extends net.minecraft.block.BlockFenceGate {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockFenceGate() {
        name = "totemTreeFenceGate";
        setUnlocalizedName(name);
        setHardness(4);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }
}
