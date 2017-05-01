package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.kordum.totemDefender.TotemDefender;

public abstract class BlockLogFace extends BlockDirectional {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLogFace(String name) {
        super(Material.wood);
        this.name = name;
        setUnlocalizedName(name);
        setCreativeTab(TotemDefender.tab);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE HANDLERS
    //
    //---------------------------------------------------------------------------

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
    }

    protected BlockState createBlockState() {
        return new BlockState(this, FACING);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }
}
