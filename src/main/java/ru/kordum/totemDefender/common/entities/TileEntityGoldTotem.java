package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.StatCollector;

public class TileEntityGoldTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityGoldTotem() {

    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public String getInventoryName() {
        return StatCollector.translateToLocalFormatted("gui.goldTotem");
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
