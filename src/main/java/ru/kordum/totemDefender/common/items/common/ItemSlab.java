package ru.kordum.totemDefender.common.items.common;

import net.minecraft.block.Block;
import ru.kordum.totemDefender.common.BlockManager;

public class ItemSlab extends net.minecraft.item.ItemSlab {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemSlab(Block block) {
        super(block, BlockManager.slab, BlockManager.doubleSlab, false);
    }
}
