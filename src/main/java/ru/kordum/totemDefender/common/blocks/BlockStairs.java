package ru.kordum.totemDefender.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import ru.kordum.totemDefender.TotemDefender;

public class BlockStairs extends net.minecraft.block.BlockStairs {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public BlockStairs(String name, IBlockState state) {
        super(state);
        setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(TotemDefender.MODID, name));
        useNeighborBrightness = true;
        setCreativeTab(TotemDefender.tab);
    }
}
