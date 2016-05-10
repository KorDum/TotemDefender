package ru.kordum.totemDefender.common.gui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;

public class ContainerGoldTotem extends ContainerTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ContainerGoldTotem(InventoryPlayer inventoryPlayer, TileEntityTotem tileEntity) {
        super(inventoryPlayer, tileEntity);
    }
}
