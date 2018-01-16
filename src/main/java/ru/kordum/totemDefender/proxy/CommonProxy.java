package ru.kordum.totemDefender.proxy;

import java.io.File;

import ru.kordum.totemDefender.block.BlockRegistry;
import ru.kordum.totemDefender.config.Config;
import ru.kordum.totemDefender.item.ItemRegistry;

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
