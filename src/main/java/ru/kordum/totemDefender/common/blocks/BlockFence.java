package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.ModBlocks;

public class BlockFence extends net.minecraft.block.BlockFence {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockFence(String name) {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(4);
        setCreativeTab(TotemDefender.tab);
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        return block == ModBlocks.fenceGate || super.canConnectTo(worldIn, pos);
    }
}
