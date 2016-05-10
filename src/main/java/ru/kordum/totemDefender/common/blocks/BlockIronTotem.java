package ru.kordum.totemDefender.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityIronTotem;

public class BlockIronTotem extends BlockTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockIronTotem(ConfigTotem config) {
        super("ironTotem", 2, config);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        TileEntityIronTotem turret = new TileEntityIronTotem();
        turret.calculateStats(this);
        return turret;
    }
}
