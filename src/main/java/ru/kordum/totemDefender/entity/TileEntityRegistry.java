package ru.kordum.totemDefender.entity;

import net.minecraftforge.fml.common.registry.GameRegistry;

import ru.kordum.totemDefender.block.BlockRegistry;

public class TileEntityRegistry {
    public static void registerEntities() {
        GameRegistry.registerTileEntity(TileEntityWoodenTotem.class, BlockRegistry.WOODEN_TOTEM.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityIronTotem.class, BlockRegistry.IRON_TOTEM.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityGoldenTotem.class, BlockRegistry.GOLDEN_TOTEM.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityDiamondTotem.class, BlockRegistry.DIAMOND_TOTEM.getUnlocalizedName());
    }
}
