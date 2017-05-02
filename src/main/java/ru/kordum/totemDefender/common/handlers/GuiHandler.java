package ru.kordum.totemDefender.common.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

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
        /*TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof TileEntityWoodenTotem) {
            return new ContainerWoodenTotem(player.inventory, (TileEntityWoodenTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityIronTotem) {
            return new ContainerIronTotem(player.inventory, (TileEntityIronTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityGoldTotem) {
            return new ContainerGoldTotem(player.inventory, (TileEntityGoldTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityDiamondTotem) {
            return new ContainerDiamondTotem(player.inventory, (TileEntityDiamondTotem) tileEntity);
        }*/

        return null;
    }

    @Override
    public Object getClientGuiElement(int i, EntityPlayer player, World world, int x, int y, int z) {
        /*TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof TileEntityWoodenTotem) {
            return new GuiWoodenTotem(player.inventory, (TileEntityWoodenTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityIronTotem) {
            return new GuiIronTotem(player.inventory, (TileEntityIronTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityGoldTotem) {
            return new GuiGoldTotem(player.inventory, (TileEntityGoldTotem) tileEntity);
        } else if (tileEntity instanceof TileEntityDiamondTotem) {
            return new GuiDiamondTotem(player.inventory, (TileEntityDiamondTotem) tileEntity);
        }*/

        return null;
    }
}
