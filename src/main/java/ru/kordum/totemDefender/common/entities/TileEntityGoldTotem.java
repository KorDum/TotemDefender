package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityGoldTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityGoldTotem() {
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
        return 3;
    }

    @Override
    public int getUpgradeSlotCount() {
        return 3;
    }
}