package ru.kordum.totemDefender.entity;

import net.minecraftforge.items.ItemStackHandler;
import ru.kordum.totemDefender.block.BlockTotem;

public class TileEntityIronTotem extends TileEntityTotem {
    public TileEntityIronTotem() {
        super();
        type = BlockTotem.EnumType.IRON;
        handler = new ItemStackHandler(type.getFilterSlots() + type.getUpgradeSlots() + 1);
    }
}
