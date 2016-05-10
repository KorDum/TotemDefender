package ru.kordum.totemDefender;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import ru.kordum.totemDefender.common.BlockManager;
import ru.kordum.totemDefender.common.CommonProxy;
import ru.kordum.totemDefender.common.ItemManager;
import ru.kordum.totemDefender.common.ModCreativeTab;
import ru.kordum.totemDefender.common.RecipeManager;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.entities.EntityProjectile;
import ru.kordum.totemDefender.common.handlres.FuelHandler;
import ru.kordum.totemDefender.common.handlres.GuiHandler;

@Mod(
    modid = TotemDefender.MODID,
    version = TotemDefender.VERSION,
    name = TotemDefender.NAME,
    dependencies = "required-after:Forge@[10.13.0.1201,)")
public class TotemDefender {
    public static final String MODID = "TotemDefender";
    public static final String NAME = "Totem Defender";
    public static final String VERSION = "1.1.7";

    @SidedProxy(
        clientSide = "ru.kordum.totemDefender.client.ClientProxy",
        serverSide = "ru.kordum.totemDefender.common.CommonProxy"
    )
    public static CommonProxy proxy;
    public static ModCreativeTab tab;
    public static TotemDefender instance;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        Config config = new Config(event.getSuggestedConfigurationFile());
        config.loadAndSave();

        tab = new ModCreativeTab();
        BlockManager.registerBlocks(config);
        ItemManager.registerItems(config);

        EntityRegistry.registerModEntity(EntityProjectile.class, "totemProjectile", 0, this, 32, 10, true);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        GameRegistry.registerFuelHandler(new FuelHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        RecipeManager.registerRecipes();

        if (event.getSide() == Side.CLIENT) {
            proxy.registerRenderThings();
        }
    }
}
