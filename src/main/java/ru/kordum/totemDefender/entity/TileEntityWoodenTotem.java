package ru.kordum.totemDefender.entity;

import net.minecraftforge.items.ItemStackHandler;
import ru.kordum.totemDefender.block.BlockTotem;

public class TileEntityWoodenTotem extends TileEntityTotem {
    public TileEntityWoodenTotem() {
        super();
        type = BlockTotem.EnumType.WOODEN;
        handler = new ItemStackHandler(type.getFilterSlots() + type.getUpgradeSlots() + 1);
    }
}
