package ru.kordum.totemDefender.common.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.kordum.totemDefender.client.gui.GuiDiamondTotem;
import ru.kordum.totemDefender.client.gui.GuiGoldTotem;
import ru.kordum.totemDefender.client.gui.GuiIronTotem;
import ru.kordum.totemDefender.client.gui.GuiWoodenTotem;
import ru.kordum.totemDefender.common.entities.TileEntityDiamondTotem;
import ru.kordum.totemDefender.common.entities.TileEntityGoldTotem;
import ru.kordum.totemDefender.common.entities.TileEntityIronTotem;
import ru.kordum.totemDefender.common.entities.TileEntityWoodenTotem;
import ru.kordum.totemDefender.common.gui.ContainerDiamondTotem;
import ru.kordum.totemDefender.common.gui.ContainerGoldTotem;
import ru.kordum.totemDefender.common.gui.ContainerIronTotem;
import ru.kordum.totemDefender.common.gui.ContainerWoodenTotem;

public class GuiHandler implements IGuiHandler {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public GuiHandler() {

    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public Object getServerGuiElement(int i, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityWoodenTotem) {
            return new ContainerWoodenTotem(player.inventory, (TileEntityWoodenTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityIronTotem) {
            return new ContainerIronTotem(player.inventory, (TileEntityIronTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityGoldTotem) {
            return new ContainerGoldTotem(player.inventory, (TileEntityGoldTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityDiamondTotem) {
            return new ContainerDiamondTotem(player.inventory, (TileEntityDiamondTotem) tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int i, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityWoodenTotem) {
            return new GuiWoodenTotem(player.inventory, (TileEntityWoodenTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityIronTotem) {
            return new GuiIronTotem(player.inventory, (TileEntityIronTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityGoldTotem) {
            return new GuiGoldTotem(player.inventory, (TileEntityGoldTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityDiamondTotem) {
            return new GuiDiamondTotem(player.inventory, (TileEntityDiamondTotem) tileEntity);
        }
        return null;
    }
}
