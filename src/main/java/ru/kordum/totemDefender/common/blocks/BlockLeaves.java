package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.BlockManager;

import java.util.List;
import java.util.Random;

public class BlockLeaves extends net.minecraft.block.BlockLeaves {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockLeaves() {
        super();
        name = "totemTreeLeaves";
        setUnlocalizedName(name);
        setGraphicsLevel(true);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
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

    protected BlockState createBlockState() {
        return new BlockState(this, CHECK_DECAY, DECAYABLE);
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
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockManager.sapling);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return Blocks.leaves.getBlockLayer();
    }
}
