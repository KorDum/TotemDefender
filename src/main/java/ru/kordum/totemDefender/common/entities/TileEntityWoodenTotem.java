package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityWoodenTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityWoodenTotem() {
        super();
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public String getName() {
        return new TextComponentTranslation("gui.wooden_totem").getFormattedText();
    }

    @Override
    public int getFilterSlotCount() {
        return 1;
    }

    @Override
    public int getUpgradeSlotCount() {
        return 1;
    }
}