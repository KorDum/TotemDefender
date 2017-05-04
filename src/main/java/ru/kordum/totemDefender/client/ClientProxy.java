package ru.kordum.totemDefender.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.client.renders.RenderTotem;
import ru.kordum.totemDefender.common.CommonProxy;
import ru.kordum.totemDefender.common.ModBlocks;
import ru.kordum.totemDefender.common.ModItems;
import ru.kordum.totemDefender.common.entities.EntityProjectile;
import ru.kordum.totemDefender.common.entities.TileEntityDiamondTotem;
import ru.kordum.totemDefender.common.entities.TileEntityGoldTotem;
import ru.kordum.totemDefender.common.entities.TileEntityIronTotem;
import ru.kordum.totemDefender.common.entities.TileEntityWoodenTotem;
import ru.kordum.totemDefender.common.handlers.GuiHandler;

public class ClientProxy extends CommonProxy {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ClientProxy() {

    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void init() {
        super.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(TotemDefender.instance, new GuiHandler());
    }

    public void registerRenderThings() {
        ModBlocks.registerRenders();
        ModItems.registerRenders();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIronTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldTotem.class, new RenderTotem());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDiamondTotem.class, new RenderTotem());

        RenderingRegistry.registerEntityRenderingHandler(
            EntityProjectile.class,
            manager -> new RenderSnowball<>(manager, Items.FIRE_CHARGE, Minecraft.getMinecraft().getRenderItem())
        );
    }
}
