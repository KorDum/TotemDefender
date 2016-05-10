package ru.kordum.totemDefender.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.kordum.totemDefender.common.entities.TileEntityDiamondTotem;
import ru.kordum.totemDefender.common.gui.ContainerDiamondTotem;

public class GuiDiamondTotem extends GuiTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public GuiDiamondTotem(InventoryPlayer inventoryPlayer, TileEntityDiamondTotem tileEntity) {
        super(new ContainerDiamondTotem(inventoryPlayer, tileEntity));
    }
}
