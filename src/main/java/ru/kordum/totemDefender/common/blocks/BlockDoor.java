package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.material.Material;
import ru.kordum.totemDefender.TotemDefender;

public class BlockDoor extends net.minecraft.block.BlockDoor {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockDoor(String name) {
        super(Material.WOOD);
        setUnlocalizedName(name);
        setRegistryName(TotemDefender.MODID, name);
        setHardness(4);
    }
}
