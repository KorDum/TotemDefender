package ru.kordum.totemDefender.common.entities;

import net.minecraft.util.StatCollector;

public class TileEntityWoodenTotem extends TileEntityTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public TileEntityWoodenTotem() {

    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public String getName() {
        return StatCollector.translateToLocalFormatted("gui.woodenTotem");
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
