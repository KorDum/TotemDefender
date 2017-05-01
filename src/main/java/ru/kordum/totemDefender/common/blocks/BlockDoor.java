package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.material.Material;

public class BlockDoor extends net.minecraft.block.BlockDoor {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockDoor() {
        super(Material.wood);
        name = "blockTotemTreeDoor";
        setUnlocalizedName(name);
        setHardness(4);
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
