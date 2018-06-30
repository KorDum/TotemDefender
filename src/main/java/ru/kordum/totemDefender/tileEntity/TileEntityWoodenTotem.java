package ru.kordum.totemDefender.tileEntity;

import ru.kordum.totemDefender.block.BlockTotem;

public class TileEntityWoodenTotem extends TileEntityTotem {
    public void init() {
        type = BlockTotem.EnumType.WOODEN;
    }
}
