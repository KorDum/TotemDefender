package ru.kordum.totemDefender.entity;

import ru.kordum.totemDefender.block.BlockTotem;

public class TileEntityGoldenTotem extends TileEntityTotem {
    public void init() {
        type = BlockTotem.EnumType.GOLDEN;
    }
}
