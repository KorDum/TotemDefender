package ru.kordum.totemDefender.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import ru.kordum.totemDefender.config.ConfigTotem;
import ru.kordum.totemDefender.entity.TileEntityIronTotem;

public class BlockIronTotem extends BlockTotem {
    public BlockIronTotem(ConfigTotem config) {
        super(LEVEL_IRON, config);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileEntityIronTotem tile = new TileEntityIronTotem();
        tile.updateState(this);
        return tile;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 100;
    }
}
