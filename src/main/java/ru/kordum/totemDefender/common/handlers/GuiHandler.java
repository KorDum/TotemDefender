package ru.kordum.totemDefender.common.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.kordum.totemDefender.client.gui.GuiTotem;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;
import ru.kordum.totemDefender.common.gui.ContainerTotem;

public class GuiHandler implements IGuiHandler {
    public static final int BLOCK_TOTEM = 0;

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
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == BLOCK_TOTEM) {
            TileEntityTotem tileEntity = (TileEntityTotem) world.getTileEntity(new BlockPos(x, y, z));
            return new ContainerTotem(player.inventory, tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == BLOCK_TOTEM) {
            TileEntityTotem tileEntity = (TileEntityTotem) world.getTileEntity(new BlockPos(x, y, z));
            return new GuiTotem(player.inventory, tileEntity);
        }
        return null;
    }
}
