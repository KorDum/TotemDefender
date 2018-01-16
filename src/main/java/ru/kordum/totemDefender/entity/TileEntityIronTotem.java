package ru.kordum.totemDefender.entity;

import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityIronTotem extends TileEntityTotem {
    public TileEntityIronTotem() {
        super();
    }

    @Override
    public String getName() {
        return new TextComponentTranslation("gui.iron_totem").getFormattedText();
    }

    @Override
    public int getFilterSlotCount() {
        return 2;
    }

    @Override
    public int getUpgradeSlotCount() {
        return 2;
    }
}
