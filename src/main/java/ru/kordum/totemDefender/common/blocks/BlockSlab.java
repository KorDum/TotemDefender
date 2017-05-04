package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.ModBlocks;

import java.util.Random;

public abstract class BlockSlab extends net.minecraft.block.BlockSlab {
    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockSlab(String name) {
        super(Material.WOOD);
        setHardness(4);
        useNeighborBrightness = true;
        setUnlocalizedName(name);
        setRegistryName(name);

        IBlockState state = blockState.getBaseState();
        if (!isDouble()) {
            state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }

        setDefaultState(state.withProperty(VARIANT, Variant.DEFAULT));
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName();
    }

    @Override
    public IProperty getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return Variant.DEFAULT;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ?
            new BlockStateContainer(this, VARIANT) :
            new BlockStateContainer(this, HALF, VARIANT);
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.slab);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = getDefaultState().withProperty(VARIANT, Variant.DEFAULT);
        if (!isDouble()) {
            state = state.withProperty(HALF, (meta & 8) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
        }
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        if (!isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
            i |= 8;
        }
        return i;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModBlocks.slab);
    }

    public static class Double extends BlockSlab {
        public Double(String name) {
            super(name);
        }

        public boolean isDouble() {
            return true;
        }
    }

    public static class Half extends BlockSlab {
        public Half(String name) {
            super(name);
        }

        public boolean isDouble() {
            return false;
        }
    }

    public enum Variant implements IStringSerializable {
        DEFAULT;

        public String getName() {
            return "default";
        }
    }
}
