package ru.kordum.totemDefender.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityDiamondTotem;

import javax.annotation.Nullable;

public class BlockDiamondTotem extends BlockTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockDiamondTotem(String name, ConfigTotem config) {
        super(name, LEVEL_DIAMOND, config);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileEntityDiamondTotem tile = new TileEntityDiamondTotem();
        tile.calculateStats(this);
        return tile;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 300;
    }
}
