package ru.kordum.totemDefender.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityWoodenTotem;

public class BlockWoodenTotem extends BlockTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockWoodenTotem(ConfigTotem config) {
        super("woodenTotem", 1, config);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public TileEntity createTileEntity(World world, int meta) {
        TileEntityWoodenTotem turret = new TileEntityWoodenTotem();
        turret.calculateStats(this);
        return turret;
    }
}
