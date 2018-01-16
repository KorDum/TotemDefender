package ru.kordum.totemDefender.block;

import net.minecraft.block.material.Material;

public class BlockFence extends net.minecraft.block.BlockFence {
    public BlockFence() {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        setHardness(4);
    }
}
