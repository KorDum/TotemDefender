package ru.kordum.totemDefender.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {
    public static void registerFurnaceRecipe() {
        GameRegistry.addSmelting(BlockRegistry.LOG, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockRegistry.LOG_FACE1, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockRegistry.LOG_FACE2, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockRegistry.LOG_FACE3, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockRegistry.WOODEN_TOTEM, new ItemStack(Items.COAL, 2, 1), 1);
        GameRegistry.addSmelting(BlockRegistry.IRON_TOTEM, new ItemStack(Items.IRON_INGOT, 4, 1), 1);
        GameRegistry.addSmelting(BlockRegistry.GOLDEN_TOTEM, new ItemStack(Items.GOLD_INGOT, 4, 1), 1);
        GameRegistry.addSmelting(BlockRegistry.DIAMOND_TOTEM, new ItemStack(Items.DIAMOND, 4, 1), 1);
    }

    public static void registerOreDictionary() {
        OreDictionary.registerOre("plankWood", BlockRegistry.PLANKS);
    }
}
