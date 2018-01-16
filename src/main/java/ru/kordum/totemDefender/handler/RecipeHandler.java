package ru.kordum.totemDefender.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {
    /*addRecipe(ModBlocks.woodenTotem, new Object[]{
        "WLW", "WCW", "WRW",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', ModItems.core,
        'R', Blocks.REDSTONE_BLOCK,
        'W', ModBlocks.log,
    });

    addRecipe(ModBlocks.ironTotem, new Object[]{
        "ILI", "WCW", "IRI",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', ModBlocks.woodenTotem,
        'R', Blocks.REDSTONE_BLOCK,
        'W', ModBlocks.log,
        'I', Items.IRON_INGOT,
    });

    addRecipe(ModBlocks.goldTotem, new Object[]{
        "ILI", "WCW", "IRI",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', ModBlocks.ironTotem,
        'R', Blocks.REDSTONE_BLOCK,
        'W', ModBlocks.log,
        'I', Items.GOLD_INGOT,
    });

    addRecipe(ModBlocks.diamondTotem, new Object[]{
        "ILI", "WCW", "IRI",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', ModBlocks.goldTotem,
        'R', Blocks.REDSTONE_BLOCK,
        'W', ModBlocks.log,
        'I', Items.DIAMOND,
    });

    addRecipe(ModItems.projectileMode, new Object[]{
        "PPP", "PCP", "PPP",
        'P', Blocks.STONE,
        'C', ModItems.core,
    });

    addRecipe(ModItems.aoeMode, new Object[]{
        "PSP", "SCS", "PSP",
        'P', ModItems.projectileMode,
        'S', Blocks.STONE,
        'C', ModItems.core,
    });

    addRecipe(ModItems.woodenASUpgrade, new Object[]{
        "P P", "PCP", "LFL",
        'P', Blocks.PLANKS,
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', ModItems.core,
        'F', Items.FEATHER,
    });

    addRecipe(ModItems.ironASUpgrade, new Object[]{
        "I I", "IPI", "LFL",
        'I', Items.IRON_INGOT,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.woodenASUpgrade,
        'F', Items.FEATHER,
    });

    addRecipe(ModItems.goldASUpgrade, new Object[]{
        "G G", "GPG", "LFL",
        'G', Items.GOLD_INGOT,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.ironASUpgrade,
        'F', Items.FEATHER,
    });

    addRecipe(ModItems.diamondASUpgrade, new Object[]{
        "D D", "DPD", "LFL",
        'D', Items.DIAMOND,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.goldASUpgrade,
        'F', Items.FEATHER,
    });

    addRecipe(ModItems.woodenDamageUpgrade, new Object[]{
        "P P", "PCP", "LFL",
        'P', Blocks.PLANKS,
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', ModItems.core,
        'F', Items.ARROW,
    });

    addRecipe(ModItems.ironDamageUpgrade, new Object[]{
        "I I", "IPI", "LFL",
        'I', Items.IRON_INGOT,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.woodenDamageUpgrade,
        'F', Items.ARROW,
    });

    addRecipe(ModItems.goldDamageUpgrade, new Object[]{
        "G G", "GPG", "LFL",
        'G', Items.GOLD_INGOT,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.ironDamageUpgrade,
        'F', Items.ARROW,
    });

    addRecipe(ModItems.diamondDamageUpgrade, new Object[]{
        "D D", "DPD", "LFL",
        'D', Items.DIAMOND,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.goldDamageUpgrade,
        'F', Items.ARROW,
    });

    addRecipe(ModItems.woodenRadiusUpgrade, new Object[]{
        "P P", "PCP", "LFL",
        'P', Blocks.PLANKS,
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', ModItems.core,
        'F', Blocks.REDSTONE_TORCH,
    });

    addRecipe(ModItems.ironRadiusUpgrade, new Object[]{
        "I I", "IPI", "LFL",
        'I', Items.IRON_INGOT,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.woodenRadiusUpgrade,
        'F', Blocks.REDSTONE_TORCH,
    });

    addRecipe(ModItems.goldRadiusUpgrade, new Object[]{
        "G G", "GPG", "LFL",
        'G', Items.GOLD_INGOT,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.ironRadiusUpgrade,
        'F', Blocks.REDSTONE_TORCH,
    });

    addRecipe(ModItems.diamondRadiusUpgrade, new Object[]{
        "D D", "DPD", "LFL",
        'D', Items.DIAMOND,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.goldRadiusUpgrade,
        'F', Blocks.REDSTONE_TORCH,
    });

    addRecipe(ModItems.poisonModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.SPIDER_EYE,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.fireModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.BLAZE_POWDER,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.lightingModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.BLAZE_ROD,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.witherModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.GHAST_TEAR,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.slowdownModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.STRING,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.blindnessModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.ENDER_EYE,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.confusionModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.FERMENTED_SPIDER_EYE,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.healModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.GOLDEN_CARROT,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.regenerationModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', Items.GOLDEN_APPLE,
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.hungryModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', new ItemStack(Items.FISH, 1, 3),
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.waterBreathingModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', new ItemStack(Items.FISH, 1, 0),
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.weaknessModifier, new Object[]{
        "D D", "DPD", "LDL",
        'D', new ItemStack(Items.SKULL, 1, 0),
        'L', new ItemStack(Items.DYE, 1, 4),
        'P', ModItems.core,
    });

    addRecipe(ModItems.enemyFilter, new Object[]{
        " L ", "BCS", " L ",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', Blocks.STONE,
        'S', Items.BONE,
        'B', Items.ROTTEN_FLESH,
    });

    addRecipe(ModItems.playerFilter, new Object[]{
        " L ", "ACS", " L ",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', Blocks.STONE,
        'S', ModItems.selfPlayerFilter,
        'A', ModItems.anotherPlayerFilter,
    });

    addRecipe(ModItems.selfPlayerFilter, new Object[]{
        " L ", "BCS", " L ",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', Blocks.STONE,
        'S', Items.WOODEN_SHOVEL,
        'B', Items.WOODEN_AXE,
    });

    addRecipe(ModItems.anotherPlayerFilter, new Object[]{
        " L ", "BCS", " L ",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', Blocks.STONE,
        'S', Items.WOODEN_HOE,
        'B', Items.WOODEN_SWORD,
    });

    addRecipe(ModItems.animalFilter, new Object[]{
        " L ", "BCS", " L ",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', Blocks.STONE,
        'S', Blocks.WOOL,
        'B', Items.LEATHER,
    });

    addRecipe(ModItems.waterFilter, new Object[]{
        " L ", "BCS", " L ",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', Blocks.STONE,
        'S', Items.FISH,
        'B', new ItemStack(Items.DYE, 1, 0),
    });

    addRecipe(ModItems.slimeFilter, new Object[]{
        " L ", "BCS", " L ",
        'L', new ItemStack(Items.DYE, 1, 4),
        'C', Blocks.STONE,
        'S', Items.SLIME_BALL,
        'B', Items.SNOWBALL,
    });*/

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
