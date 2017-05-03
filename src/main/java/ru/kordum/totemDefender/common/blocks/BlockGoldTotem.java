package ru.kordum.totemDefender.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityGoldTotem;

import javax.annotation.Nullable;

public class BlockGoldTotem extends BlockTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockGoldTotem(String name, ConfigTotem config) {
        super(name, LEVEL_GOLD, config);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileEntityGoldTotem tile = new TileEntityGoldTotem();
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
        return 150;
    }
}
