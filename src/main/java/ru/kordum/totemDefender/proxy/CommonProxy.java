package ru.kordum.totemDefender.proxy;

import java.io.File;

import ru.kordum.totemDefender.handler.BlockRegistry;
import ru.kordum.totemDefender.config.Config;
import ru.kordum.totemDefender.handler.RecipeHandler;
import ru.kordum.totemDefender.handler.ItemRegistry;

public abstract class CommonProxy {
    public void preInit(File configFile) {
        Config config = new Config(configFile);
        config.loadAndSave();
        BlockRegistry.init(config);
        ItemRegistry.init(config);
    }
    
    public void init() {
        RecipeHandler.registerFurnaceRecipe();
        RecipeHandler.registerOreDictionary();
    }

    public void registerRenderThings() {
        // ignored
    }
}