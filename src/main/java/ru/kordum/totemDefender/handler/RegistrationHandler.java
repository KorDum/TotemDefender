package ru.kordum.totemDefender.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.entity.EntityProjectile;

@Mod.EventBusSubscriber
public class RegistrationHandler {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BlockRegistry.registerBlocks(event.getRegistry());
        TileEntityRegistry.registerEntities();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ItemRegistry.registerItems(event.getRegistry());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        TileEntityRegistry.registerRenders();
        ItemRegistry.registerRenders();

        EntityRegistry.registerModEntity(
            new ResourceLocation(TotemDefender.MODID, "totem_projectile"),
            EntityProjectile.class,
            "totem_projectile",
            0,
            TotemDefender.instance,
            32, 5, true
        );
    }
}
