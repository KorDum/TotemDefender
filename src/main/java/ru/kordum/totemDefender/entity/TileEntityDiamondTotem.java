package ru.kordum.totemDefender.entity;

import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityDiamondTotem extends TileEntityTotem {
    public TileEntityDiamondTotem() {
        super();
    }

    @Override
    public String getName() {
        return new TextComponentTranslation("gui.diamond_totem").getFormattedText();
    }

    @Override
    public int getFilterSlotCount() {
        return 4;
    }

    @Override
    public int getUpgradeSlotCount() {
        return 4;
    }
}
