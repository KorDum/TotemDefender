package ru.kordum.totemDefender.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import ru.kordum.totemDefender.entity.EntityProjectile;
import ru.kordum.totemDefender.entity.TileEntityDiamondTotem;
import ru.kordum.totemDefender.entity.TileEntityGoldenTotem;
import ru.kordum.totemDefender.entity.TileEntityIronTotem;
import ru.kordum.totemDefender.entity.TileEntityWoodenTotem;
import ru.kordum.totemDefender.render.RenderTotem;

public class TileEntityRegistry {
    public static void registerEntities() {
        GameRegistry.registerTileEntity(TileEntityWoodenTotem.class, BlockRegistry.WOODEN_TOTEM.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityIronTotem.class, BlockRegistry.IRON_TOTEM.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityGoldenTotem.class, BlockRegistry.GOLDEN_TOTEM.getUnlocalizedName());
        GameRegistry.registerTileEntity(TileEntityDiamondTotem.class, BlockRegistry.DIAMOND_TOTEM.getUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldenTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDiamondTotem.class, new RenderTotem());

        RenderingRegistry.registerEntityRenderingHandler(
            EntityProjectile.class,
            manager -> new RenderSnowball<>(manager, Items.FIRE_CHARGE, Minecraft.getMinecraft().getRenderItem())
        );
    }
}
