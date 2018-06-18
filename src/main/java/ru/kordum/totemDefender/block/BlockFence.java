package ru.kordum.totemDefender.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockFence extends net.minecraft.block.BlockFence {
    public BlockFence() {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        setHardness(4);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        // hack for https://github.com/KorDum/TotemDefender/issues/4
        name = "totem_" + name;
        return super.setUnlocalizedName(name);
    }
}
