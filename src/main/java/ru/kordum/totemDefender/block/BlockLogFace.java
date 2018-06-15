package ru.kordum.totemDefender.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLogFace extends BlockDirectional {
    public BlockLogFace() {
        super(Material.WOOD);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (world.isRemote) {
            return;
        }

        IBlockState state1 = world.getBlockState(pos.north());
        IBlockState state2 = world.getBlockState(pos.south());
        IBlockState state3 = world.getBlockState(pos.west());
        IBlockState state4 = world.getBlockState(pos.east());
        EnumFacing facing = state.getValue(FACING);

        if (facing == EnumFacing.NORTH && state1.isFullBlock() && !state2.isFullBlock()) {
            facing = EnumFacing.SOUTH;
        } else if (facing == EnumFacing.SOUTH && state2.isFullBlock() && !state1.isFullBlock()) {
            facing = EnumFacing.NORTH;
        } else if (facing == EnumFacing.WEST && state3.isFullBlock() && !state4.isFullBlock()) {
            facing = EnumFacing.EAST;
        } else if (facing == EnumFacing.EAST && state4.isFullBlock() && !state3.isFullBlock()) {
            facing = EnumFacing.WEST;
        }

        world.setBlockState(pos, state.withProperty(FACING, facing), 2);
    }

    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withProperty(FACING, mirrorIn.mirror(state.getValue(FACING)));
    }

    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
