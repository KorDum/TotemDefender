package ru.kordum.totemDefender.proxy;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.config.Config;
import ru.kordum.totemDefender.handler.BlockRegistry;
import ru.kordum.totemDefender.handler.GuiHandler;
import ru.kordum.totemDefender.handler.ItemRegistry;
import ru.kordum.totemDefender.handler.RecipeHandler;

import java.io.File;

public abstract class CommonProxy {
    public void preInit(File configFile) {
        Config config = new Config(configFile);
        BlockRegistry.init(config);
        ItemRegistry.init(config);
    }

    public void init() {
        RecipeHandler.registerFurnaceRecipe();
        RecipeHandler.registerOreDictionary();
        NetworkRegistry.INSTANCE.registerGuiHandler(TotemDefender.instance, new GuiHandler());
    }
}
