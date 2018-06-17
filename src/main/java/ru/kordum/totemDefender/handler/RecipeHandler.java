package ru.kordum.totemDefender.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {
    public static void registerFurnaceRecipe() {
        GameRegistry.addSmelting(BlockRegistry.LOG, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockRegistry.TOTEM, new ItemStack(Items.COAL, 2, 16), 3.0f);
    }

    public static void registerOreDictionary() {
        OreDictionary.registerOre("plankWood", BlockRegistry.PLANKS);
    }
}
