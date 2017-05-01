package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.StatCollector;

public class TileEntityDiamondTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityDiamondTotem() {

    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public String getName() {
        return StatCollector.translateToLocalFormatted("gui.diamondTotem");
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
