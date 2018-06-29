package ru.kordum.totemDefender.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.kordum.totemDefender.handler.ItemRegistry;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockDoor extends net.minecraft.block.BlockDoor {
    public BlockDoor() {
        super(Material.WOOD);
        setHardness(4);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(HALF) == EnumDoorHalf.UPPER ? Items.AIR : getItem();
    }

    @Nonnull
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(getItem());
    }

    private Item getItem() {
        return ItemRegistry.DOOR;
    }
}
