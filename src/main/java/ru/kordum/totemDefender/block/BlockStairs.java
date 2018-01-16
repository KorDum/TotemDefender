package ru.kordum.totemDefender.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import ru.kordum.totemDefender.TotemDefender;

public class BlockStairs extends net.minecraft.block.BlockStairs {
    public BlockStairs(IBlockState state) {
        super(state);
        useNeighborBrightness = true;
    }
}
