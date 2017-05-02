package ru.kordum.totemDefender;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import ru.kordum.totemDefender.common.BlockManager;
import ru.kordum.totemDefender.common.CommonProxy;
import ru.kordum.totemDefender.common.ItemManager;
import ru.kordum.totemDefender.common.ModCreativeTab;
import ru.kordum.totemDefender.common.RecipeManager;
import ru.kordum.totemDefender.common.config.Config;

@Mod(
    modid = TotemDefender.MODID,
    version = TotemDefender.VERSION,
    name = TotemDefender.NAME
)
public class TotemDefender {
    public static final String MODID = "totemdefender";
    public static final String NAME = "Totem Defender";
    public static final String VERSION = "1.2.0";

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
//        EntityRegistry.registerModEntity(EntityProjectile.class, "totemProjectile", 0, this, 32, 10, true);
//        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        RecipeManager.registerRecipes();

        if (event.getSide() == Side.CLIENT) {
            BlockManager.registerRenders();
            ItemManager.registerMeshes();
            proxy.registerRenderThings();
        }
    }
}
