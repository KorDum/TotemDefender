package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.TotemDefender;

import javax.annotation.Nullable;

public class BlockFenceGate extends BlockHorizontal {
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");
    protected static final AxisAlignedBB AABB_COLLIDE_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB AABB_COLLIDE_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_COLLIDE_ZAXIS_INWALL = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
    protected static final AxisAlignedBB AABB_COLLIDE_XAXIS_INWALL = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
    protected static final AxisAlignedBB AABB_CLOSED_SELECTED_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
    protected static final AxisAlignedBB AABB_CLOSED_SELECTED_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockFenceGate(String name) {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        IBlockState state = blockState.getBaseState()
            .withProperty(OPEN, false)
            .withProperty(POWERED, false)
            .withProperty(IN_WALL, false);

        setDefaultState(state);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(4);
        setCreativeTab(TotemDefender.tab);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        state = this.getActualState(state, source, pos);
        if (state.getValue(IN_WALL).booleanValue()) {
            return state.getValue(FACING).getAxis() == EnumFacing.Axis.X ? AABB_COLLIDE_XAXIS_INWALL : AABB_COLLIDE_ZAXIS_INWALL;
        }
        return state.getValue(FACING).getAxis() == EnumFacing.Axis.X
            ? AABB_COLLIDE_XAXIS
            : AABB_COLLIDE_ZAXIS;
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        EnumFacing.Axis enumfacing$axis = state.getValue(FACING).getAxis();

        if (enumfacing$axis == EnumFacing.Axis.Z && (canFenceGateConnectTo(worldIn, pos, EnumFacing.WEST) || canFenceGateConnectTo(worldIn, pos, EnumFacing.EAST)) || enumfacing$axis == EnumFacing.Axis.X && (canFenceGateConnectTo(worldIn, pos, EnumFacing.NORTH) || canFenceGateConnectTo(worldIn, pos, EnumFacing.SOUTH))) {
            state = state.withProperty(IN_WALL, Boolean.valueOf(true));
        }

        return state;
    }

    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getMaterial().isSolid() ? super.canPlaceBlockAt(worldIn, pos) : false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        if (blockState.getValue(OPEN).booleanValue()) {
            return NULL_AABB;
        }
        return blockState.getValue(FACING).getAxis() == EnumFacing.Axis.Z
            ? AABB_CLOSED_SELECTED_ZAXIS
            : AABB_CLOSED_SELECTED_XAXIS;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getValue(OPEN).booleanValue();
    }

    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        boolean flag = world.isBlockPowered(pos);
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(OPEN, Boolean.valueOf(flag)).withProperty(POWERED, Boolean.valueOf(flag)).withProperty(IN_WALL, Boolean.valueOf(false));
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (state.getValue(OPEN).booleanValue()) {
            state = state.withProperty(OPEN, Boolean.valueOf(false));
            world.setBlockState(pos, state, 10);
        } else {
            EnumFacing enumfacing = EnumFacing.fromAngle((double) playerIn.rotationYaw);
            if (state.getValue(FACING) == enumfacing.getOpposite()) {
                state = state.withProperty(FACING, enumfacing);
            }

            state = state.withProperty(OPEN, Boolean.valueOf(true));
            world.setBlockState(pos, state, 10);
        }

        world.playEvent(playerIn, state.getValue(OPEN).booleanValue() ? 1008 : 1014, pos, 0);
        return true;
    }

    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!world.isRemote) {
            boolean flag = world.isBlockPowered(pos);
            if (state.getValue(POWERED).booleanValue() != flag) {
                world.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag))
                    .withProperty(OPEN, Boolean.valueOf(flag)), 2);

                if (state.getValue(OPEN).booleanValue() != flag) {
                    world.playEvent(null, flag ? 1008 : 1014, pos, 0);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
            .withProperty(FACING, EnumFacing.getHorizontal(meta))
            .withProperty(OPEN, Boolean.valueOf((meta & 4) != 0))
            .withProperty(POWERED, Boolean.valueOf((meta & 8) != 0));
    }

    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();

        if (state.getValue(POWERED).booleanValue()) {
            i |= 8;
        }
        if (state.getValue(OPEN).booleanValue()) {
            i |= 4;
        }
        return i;
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, OPEN, POWERED, IN_WALL);
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        Block connector = world.getBlockState(pos.offset(facing)).getBlock();
        return connector instanceof BlockFence
            || connector instanceof BlockWall;
    }

    private boolean canFenceGateConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        Block block = world.getBlockState(pos.offset(facing)).getBlock();
        return block.canBeConnectedTo(world, pos.offset(facing), facing.getOpposite());
    }
}
