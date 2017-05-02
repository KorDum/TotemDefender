package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.material.Material;
import ru.kordum.totemDefender.TotemDefender;

public class BlockFence extends net.minecraft.block.BlockFence {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockFence(String name) {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(4);
        setCreativeTab(TotemDefender.tab);
    }
}
