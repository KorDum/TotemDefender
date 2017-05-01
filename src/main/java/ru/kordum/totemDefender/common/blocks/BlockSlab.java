package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.BlockManager;

import java.util.Random;

public class BlockSlab extends net.minecraft.block.BlockSlab {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockSlab(String name) {
        super(Material.wood);
        this.name = name;
        setUnlocalizedName(name);
        setHardness(4);
        useNeighborBrightness = true;

        IBlockState iblockstate = blockState.getBaseState();

        if (!isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
            setCreativeTab(TotemDefender.tab);
        }

        setDefaultState(iblockstate);
    }

    public BlockSlab() {
        this("totemTreeSlab");
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState iblockstate = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)
            .withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);

        if (isDouble()) {
            return iblockstate;
        }
        else if (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D)) {
            return iblockstate;
        }

        return iblockstate.withProperty(HALF, EnumBlockHalf.TOP);
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return name;
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public IProperty getVariantProperty() {
        return null;
    }

    @Override
    public Object getVariant(ItemStack stack) {
        return null;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockManager.slab);
    }

    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = getDefaultState();

        if (!isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ?
                EnumBlockHalf.BOTTOM :
                EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (!isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockState createBlockState() {
        return isDouble() ?
            new BlockState(this) :
            new BlockState(this, HALF);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(BlockManager.slab);
    }
}
