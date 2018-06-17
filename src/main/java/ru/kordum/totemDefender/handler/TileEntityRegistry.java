package ru.kordum.totemDefender.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.entity.EntityProjectile;
import ru.kordum.totemDefender.entity.TileEntityDiamondTotem;
import ru.kordum.totemDefender.entity.TileEntityGoldenTotem;
import ru.kordum.totemDefender.entity.TileEntityIronTotem;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.entity.TileEntityWoodenTotem;
import ru.kordum.totemDefender.render.RenderTotem;

public class TileEntityRegistry {
    public static void registerEntities() {
        GameRegistry.registerTileEntity(
            TileEntityWoodenTotem.class,
            new ResourceLocation(TotemDefender.MODID, BlockRegistry.TOTEM.getUnlocalizedName() + "_" + BlockTotem.EnumType.WOODEN.getName())
        );
        GameRegistry.registerTileEntity(
            TileEntityIronTotem.class,
            new ResourceLocation(TotemDefender.MODID, BlockRegistry.TOTEM.getUnlocalizedName() + "_" + BlockTotem.EnumType.IRON.getName())
        );
        GameRegistry.registerTileEntity(
            TileEntityGoldenTotem.class,
            new ResourceLocation(TotemDefender.MODID, BlockRegistry.TOTEM.getUnlocalizedName() + "_" + BlockTotem.EnumType.GOLDEN.getName())
        );
        GameRegistry.registerTileEntity(
            TileEntityDiamondTotem.class,
            new ResourceLocation(TotemDefender.MODID, BlockRegistry.TOTEM.getUnlocalizedName() + "_" + BlockTotem.EnumType.DIAMOND.getName())
        );
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTotem.class, new RenderTotem());

        RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, manager -> new RenderSnowball<>(manager, Items.FIRE_CHARGE, Minecraft.getMinecraft().getRenderItem()));
    }
}
