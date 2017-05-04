package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.generators.WorldGenTotemTree;

import java.util.Random;

public class BlockSapling extends BlockBush implements IGrowable {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockPlanks.EnumType.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockSapling(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(TotemDefender.tab);

        setDefaultState(
            blockState.getBaseState()
                .withProperty(TYPE, BlockPlanks.EnumType.TOTEM)
                .withProperty(STAGE, 0)
        );
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, pos, state, rand);
            if (world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(100) == 0) {
                grow(world, rand, pos, state);
            }
        }
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE, STAGE);
    }

    private void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (TerrainGen.saplingGrowTree(worldIn, rand, pos)) {
            WorldGenTotemTree worldGen = new WorldGenTotemTree();
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);
            worldGen.generate(worldIn, rand, pos);
        }
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        if (state.getValue(STAGE).intValue() == 0) {
            world.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(world, pos, state, rand);
        }
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.05;
    }
}
