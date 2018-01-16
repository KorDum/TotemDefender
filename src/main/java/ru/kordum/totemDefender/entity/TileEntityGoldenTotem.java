package ru.kordum.totemDefender.entity;

import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityGoldenTotem extends TileEntityTotem {
    public TileEntityGoldenTotem() {
        super();
    }

    @Override
    public String getName() {
        return new TextComponentTranslation("gui.wooden_totem").getFormattedText();
    }

    @Override
    public int getFilterSlotCount() {
        return 3;
    }

    @Override
    public int getUpgradeSlotCount() {
        return 3;
    }
}
