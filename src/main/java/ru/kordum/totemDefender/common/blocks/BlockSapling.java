package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.generators.WorldGenTotemTree;

import java.util.Random;

public class BlockSapling extends BlockBush implements IGrowable {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockPlanks.EnumType.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockSapling() {
        float f = 0.4F;
        name = "totemTreeSapling";
        setUnlocalizedName(name);
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setStepSound(soundTypeGrass);
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
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLight(pos.up()) >= 9 && rand.nextInt(100) == 0) {
                generateTree(worldIn, pos, state, rand);
            }
        }
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    protected BlockState createBlockState() {
        return new BlockState(this, TYPE, STAGE);
    }

    private void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (TerrainGen.saplingGrowTree(worldIn, rand, pos)) {
            WorldGenTotemTree worldGen = new WorldGenTotemTree();
            worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 0);
            worldGen.generate(worldIn, rand, pos);
        }
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if ((Integer) state.getValue(STAGE) == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            generateTree(worldIn, pos, state, rand);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta);
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

    public String getName() {
        return name;
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
