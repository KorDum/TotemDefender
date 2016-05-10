package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.StatCollector;

public class TileEntityIronTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityIronTotem() {

    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public String getInventoryName() {
        return StatCollector.translateToLocalFormatted("gui.ironTotem");
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
