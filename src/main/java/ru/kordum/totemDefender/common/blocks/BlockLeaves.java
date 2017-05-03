package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.ModBlocks;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class BlockLeaves extends net.minecraft.block.BlockLeaves {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLeaves(String name) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
        setGraphicsLevel(true);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return null;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
            .withProperty(CHECK_DECAY, true)
            .withProperty(DECAYABLE, true);
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.sapling);
    }
}
