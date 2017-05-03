package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityDiamondTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityDiamondTotem() {
        super();
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

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