package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import ru.kordum.totemDefender.TotemDefender;

public class BlockFence extends net.minecraft.block.BlockFence {
    private String name;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockFence() {
        super(TotemDefender.MODID + ":totemTreePlanks", Material.wood);
        name = "totemTreeFence";
        setBlockName(name);
        setHardness(4);
        setCreativeTab(TotemDefender.tab);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);

        return block == this
            || block instanceof net.minecraft.block.BlockFenceGate
            || (
                block.getMaterial().isOpaque() &&
                    block.renderAsNormalBlock() &&
                    block.getMaterial() != Material.gourd
            );
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
