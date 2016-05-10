package ru.kordum.totemDefender.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.kordum.totemDefender.common.entities.TileEntityGoldTotem;
import ru.kordum.totemDefender.common.gui.ContainerGoldTotem;

public class GuiGoldTotem extends GuiTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public GuiGoldTotem(InventoryPlayer inventoryPlayer, TileEntityGoldTotem tileEntity) {
        super(new ContainerGoldTotem(inventoryPlayer, tileEntity));
    }
}
