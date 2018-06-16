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
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.render.RenderTotem;

public class TileEntityRegistry {
    public static void registerEntities() {
        GameRegistry.registerTileEntity(TileEntityTotem.class, BlockRegistry.TOTEM.getUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTotem.class, new RenderTotem());

        RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, manager -> new RenderSnowball<>(manager, Items.FIRE_CHARGE, Minecraft.getMinecraft().getRenderItem()));
    }
}
