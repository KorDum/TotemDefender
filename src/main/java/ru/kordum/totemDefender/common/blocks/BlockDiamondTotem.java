package ru.kordum.totemDefender.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityDiamondTotem;

public class BlockDiamondTotem extends BlockTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockDiamondTotem(ConfigTotem config) {
        super("diamondTotem", 4, config);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        TileEntityDiamondTotem turret = new TileEntityDiamondTotem();
        turret.calculateStats(this);
        return turret;
    }
}
