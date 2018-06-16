package ru.kordum.totemDefender.entity;

import net.minecraftforge.items.ItemStackHandler;
import ru.kordum.totemDefender.block.BlockTotem;

public class TileEntityGoldenTotem extends TileEntityTotem {
    public TileEntityGoldenTotem() {
        super();
        type = BlockTotem.EnumType.GOLDEN;
        handler = new ItemStackHandler(type.getFilterSlots() + type.getUpgradeSlots() + 1);
    }
}
