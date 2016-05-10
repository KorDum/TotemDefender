package ru.kordum.totemDefender.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.kordum.totemDefender.common.entities.TileEntityWoodenTotem;
import ru.kordum.totemDefender.common.gui.ContainerWoodenTotem;

public class GuiWoodenTotem extends GuiTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public GuiWoodenTotem(InventoryPlayer inventoryPlayer, TileEntityWoodenTotem tileEntity) {
        super(new ContainerWoodenTotem(inventoryPlayer, tileEntity));
    }
}
