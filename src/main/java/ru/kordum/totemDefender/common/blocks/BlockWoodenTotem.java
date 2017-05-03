package ru.kordum.totemDefender.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.kordum.totemDefender.common.config.ConfigTotem;
import ru.kordum.totemDefender.common.entities.TileEntityWoodenTotem;

import javax.annotation.Nullable;

public class BlockWoodenTotem extends BlockTotem {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockWoodenTotem(String name, ConfigTotem config) {
        super(name, LEVEL_WOODEN, config);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileEntityWoodenTotem tile = new TileEntityWoodenTotem();
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
        return 20;
    }
}
