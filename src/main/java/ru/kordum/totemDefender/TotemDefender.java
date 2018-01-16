package ru.kordum.totemDefender;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import ru.kordum.totemDefender.proxy.CommonProxy;

@Mod(
    modid = TotemDefender.MODID,
    version = TotemDefender.VERSION,
    name = TotemDefender.NAME
)
public class TotemDefender {
    public static final String MODID = "totemdefender";
    public static final String NAME = "Totem Defender";
    public static final String VERSION = "1.2.4";

    @SidedProxy(
        clientSide = "ru.kordum.totemDefender.proxy.ClientProxy",
        serverSide = "ru.kordum.totemDefender.proxy.ServerProxy"
    )
    public static CommonProxy proxy;
    public static ModCreativeTab tab;
    public static TotemDefender instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        tab = new ModCreativeTab();
        proxy.preInit(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
