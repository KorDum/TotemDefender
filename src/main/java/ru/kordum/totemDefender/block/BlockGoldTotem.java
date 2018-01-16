package ru.kordum.totemDefender.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import ru.kordum.totemDefender.config.ConfigTotem;
import ru.kordum.totemDefender.entity.TileEntityGoldenTotem;

public class BlockGoldTotem extends BlockTotem {
    public BlockGoldTotem(ConfigTotem config) {
        super(LEVEL_GOLD, config);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        TileEntityGoldenTotem tile = new TileEntityGoldenTotem();
        tile.updateState(this);
        return tile;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 150;
    }
}
