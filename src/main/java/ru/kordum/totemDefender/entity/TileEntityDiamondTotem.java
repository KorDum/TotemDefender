package ru.kordum.totemDefender.entity;

import net.minecraftforge.items.ItemStackHandler;
import ru.kordum.totemDefender.block.BlockTotem;

public class TileEntityDiamondTotem extends TileEntityTotem {
    public TileEntityDiamondTotem() {
        super();
        type = BlockTotem.EnumType.DIAMOND;
        handler = new ItemStackHandler(type.getFilterSlots() + type.getUpgradeSlots() + 1);
    }
}
