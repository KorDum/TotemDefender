package ru.kordum.totemDefender.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.kordum.totemDefender.common.entities.TileEntityIronTotem;
import ru.kordum.totemDefender.common.gui.ContainerIronTotem;

public class GuiIronTotem extends GuiTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public GuiIronTotem(InventoryPlayer inventoryPlayer, TileEntityIronTotem tileEntity) {
        super(new ContainerIronTotem(inventoryPlayer, tileEntity));
    }
}
