package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityIronTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityIronTotem() {
        super();
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

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