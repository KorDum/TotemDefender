package ru.kordum.totemDefender.common.items.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.common.BlockManager;
import ru.kordum.totemDefender.common.blocks.BlockSlab;

public class ItemSlab extends ItemBlock {
    private final BlockSlab slab;
    private final BlockSlab doubleSlab;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemSlab(Block block) {
        super(block);
        slab = BlockManager.slab;
        doubleSlab = BlockManager.doubleSlab;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
        IBlockState blockState = worldIn.getBlockState(pos);
        BlockPos blockpos1 = pos;

        if (blockState.getBlock() == slab) {
            boolean flag = blockState.getValue(BlockSlab.HALF) == EnumBlockHalf.TOP;

            if ((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag)) {
                return true;
            }
        }

        pos = pos.offset(side);
        IBlockState blockState1 = worldIn.getBlockState(pos);
        return blockState1.getBlock() == slab || super.canPlaceBlockOnSide(worldIn, blockpos1, side, player, stack);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (stack.stackSize == 0 || !playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
            return false;
        }

        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == slab) {
            EnumBlockHalf enumblockhalf = (EnumBlockHalf) iblockstate.getValue(BlockSlab.HALF);

            if ((side == EnumFacing.UP && enumblockhalf == EnumBlockHalf.BOTTOM ||
                side == EnumFacing.DOWN && enumblockhalf == EnumBlockHalf.TOP)) {
                IBlockState iblockstate1 = doubleSlab.getDefaultState();

                addSlab(stack, worldIn, pos, iblockstate1);
                return true;
            }
        }

        return tryPlace(stack, worldIn, pos.offset(side)) || super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    private boolean tryPlace(ItemStack stack, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == slab) {
            IBlockState iblockstate1 = doubleSlab.getDefaultState();

            addSlab(stack, worldIn, pos, iblockstate1);
            return true;
        }

        return false;
    }

    private void addSlab(ItemStack stack, World worldIn, BlockPos pos, IBlockState iblockstate1) {
        if (worldIn.checkNoEntityCollision(doubleSlab.getCollisionBoundingBox(worldIn, pos, iblockstate1)) &&
            worldIn.setBlockState(pos, iblockstate1, 3)) {
            worldIn.playSoundEffect(
                (double) pos.getX() + 0.5F,
                (double) pos.getY() + 0.5F,
                (double) pos.getZ() + 0.5F,
                doubleSlab.stepSound.getPlaceSound(),
                (doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F,
                doubleSlab.stepSound.getFrequency() * 0.8F
            );
            stack.stackSize--;
        }
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public String getUnlocalizedName(ItemStack stack) {
        return slab.getUnlocalizedName(stack.getMetadata());
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public int getMetadata(int damage) {
        return damage;
    }
}
