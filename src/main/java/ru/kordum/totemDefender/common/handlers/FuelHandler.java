package ru.kordum.totemDefender.common.handlers;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.kordum.totemDefender.common.BlockManager;

public class FuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {
        Item item = fuel.getItem();
        Block block = Block.getBlockFromItem(item);
        if (block != null && block.equals(BlockManager.sapling)) {
            return 150;
        }

        return 0;
    }
}