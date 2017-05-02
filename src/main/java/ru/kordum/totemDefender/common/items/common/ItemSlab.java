package ru.kordum.totemDefender.common.items.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemSlab extends net.minecraft.item.ItemSlab {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemSlab(String name, Block block, BlockSlab slab, BlockSlab doubleSlab) {
        super(block, slab, doubleSlab);
        setUnlocalizedName(name);
        setRegistryName(name);
        GameRegistry.register(this);
    }
}
