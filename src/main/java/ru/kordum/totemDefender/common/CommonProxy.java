package ru.kordum.totemDefender.common;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.entities.EntityProjectile;
import ru.kordum.totemDefender.common.handlers.RecipeHandler;

import java.io.File;

public class CommonProxy {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public CommonProxy() {

    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------
    
    public void preInit(File configFile) {
        Config config = new Config(configFile);
        config.loadAndSave();

        ModBlocks.registerBlocks(config);
        ModItems.registerItems(config);

        EntityRegistry.registerModEntity(
            new ResourceLocation(TotemDefender.MODID, "totem_projectile"),
            EntityProjectile.class,
            "totem_projectile",
            0,
            TotemDefender.instance,
            32, 5, true
        );
    }
    
    public void init() {
        RecipeHandler.registerCraftingRecipes();
        RecipeHandler.registerFurnaceRecipe();
        RecipeHandler.registerOreDictionary();
    }

    public void registerRenderThings() {
        // ignored
    }
}
