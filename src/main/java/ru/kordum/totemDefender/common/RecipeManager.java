package ru.kordum.totemDefender.common;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeManager {
    public static void registerRecipes() {
        /*OreDictionary.registerOre("plankWood", BlockManager.planks);
        GameRegistry.addSmelting(BlockManager.log, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockManager.logFace1, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockManager.logFace2, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockManager.logFace3, new ItemStack(Items.COAL, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockManager.woodenTotem, new ItemStack(Items.COAL, 2, 1), 1);
        GameRegistry.addSmelting(BlockManager.ironTotem, new ItemStack(Items.IRON_INGOT, 4, 1), 1);
        GameRegistry.addSmelting(BlockManager.goldTotem, new ItemStack(Items.GOLD_INGOT, 4, 1), 1);
        GameRegistry.addSmelting(BlockManager.diamondTotem, new ItemStack(Items.DIAMOND, 4, 1), 1);*/

        /*
         * Common
         */
        addRecipe(ModItems.core, new Object[]{
            "L L", "LEL", "LRL",
            'L', new ItemStack(Items.DYE, 1, 4),
            'E', Items.ENDER_PEARL,
            'R', Blocks.REDSTONE_BLOCK,
        });

        /*addRecipe(BlockManager.sapling, new Object[]{
            "RRR", "RSR", "RRR",
            'S', new ItemStack(Blocks.SAPLING, 1, 1),
            'R', Items.REDSTONE,
        });

        addRecipe(new ItemStack(BlockManager.stairs, 4), new Object[]{
            "P  ", "PP ", "PPP",
            'P', BlockManager.planks,
        });

        addRecipe(new ItemStack(BlockManager.slab, 6), new Object[]{
            "PPP",
            'P', BlockManager.planks,
        });

        addRecipe(new ItemStack(BlockManager.fence, 4), new Object[]{
            "PPP", "P P",
            'P', BlockManager.planks,
        });

        addRecipe(BlockManager.fenceGate, new Object[]{
            "FPF",
            'P', BlockManager.planks,
            'F', BlockManager.fence,
        });

        addRecipe(ItemManager.door, new Object[]{
            "PP", "PP", "PP",
            'P', BlockManager.planks,
        });

        addShapelessRecipe(BlockManager.planks, 4, BlockManager.log);
        addShapelessRecipe(BlockManager.planks, 4, BlockManager.logFace1);
        addShapelessRecipe(BlockManager.planks, 4, BlockManager.logFace2);
        addShapelessRecipe(BlockManager.planks, 4, BlockManager.logFace3);*/

        /*
         * Totems
         */
        /*addRecipe(BlockManager.woodenTotem, new Object[]{
            "WLW", "WCW", "WRW",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', ItemManager.core,
            'R', Blocks.REDSTONE_BLOCK,
            'W', BlockManager.log,
        });

        addRecipe(BlockManager.ironTotem, new Object[]{
            "ILI", "WCW", "IRI",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', BlockManager.woodenTotem,
            'R', Blocks.REDSTONE_BLOCK,
            'W', BlockManager.log,
            'I', Items.IRON_INGOT,
        });

        addRecipe(BlockManager.goldTotem, new Object[]{
            "ILI", "WCW", "IRI",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', BlockManager.ironTotem,
            'R', Blocks.REDSTONE_BLOCK,
            'W', BlockManager.log,
            'I', Items.GOLD_INGOT,
        });

        addRecipe(BlockManager.diamondTotem, new Object[]{
            "ILI", "WCW", "IRI",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', BlockManager.goldTotem,
            'R', Blocks.REDSTONE_BLOCK,
            'W', BlockManager.log,
            'I', Items.DIAMOND,
        });*/

        /*
         * Modes
         */
        /*addRecipe(ItemManager.projectileMode, new Object[]{
            "PPP", "PCP", "PPP",
            'P', Blocks.STONE,
            'C', ItemManager.core,
        });

        addRecipe(ItemManager.aoeMode, new Object[]{
            "PSP", "SCS", "PSP",
            'P', ItemManager.projectileMode,
            'S', Blocks.STONE,
            'C', ItemManager.core,
        });*/

        /*
         * AS Upgrades
         */
        /*addRecipe(ItemManager.woodenASUpgrade, new Object[]{
            "P P", "PCP", "LFL",
            'P', Blocks.PLANKS,
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', ItemManager.core,
            'F', Items.FEATHER,
        });

        addRecipe(ItemManager.ironASUpgrade, new Object[]{
            "I I", "IPI", "LFL",
            'I', Items.IRON_INGOT,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.woodenASUpgrade,
            'F', Items.FEATHER,
        });

        addRecipe(ItemManager.goldASUpgrade, new Object[]{
            "G G", "GPG", "LFL",
            'G', Items.GOLD_INGOT,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.ironASUpgrade,
            'F', Items.FEATHER,
        });

        addRecipe(ItemManager.diamondASUpgrade, new Object[]{
            "D D", "DPD", "LFL",
            'D', Items.DIAMOND,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.goldASUpgrade,
            'F', Items.FEATHER,
        });*/

        /*
         * Damage Upgrades
         */
        /*addRecipe(ItemManager.woodenDamageUpgrade, new Object[]{
            "P P", "PCP", "LFL",
            'P', Blocks.PLANKS,
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', ItemManager.core,
            'F', Items.ARROW,
        });

        addRecipe(ItemManager.ironDamageUpgrade, new Object[]{
            "I I", "IPI", "LFL",
            'I', Items.IRON_INGOT,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.woodenDamageUpgrade,
            'F', Items.ARROW,
        });

        addRecipe(ItemManager.goldDamageUpgrade, new Object[]{
            "G G", "GPG", "LFL",
            'G', Items.GOLD_INGOT,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.ironDamageUpgrade,
            'F', Items.ARROW,
        });

        addRecipe(ItemManager.diamondDamageUpgrade, new Object[]{
            "D D", "DPD", "LFL",
            'D', Items.DIAMOND,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.goldDamageUpgrade,
            'F', Items.ARROW,
        });*/

        /*
         * Radius Upgrades
         */
        /*addRecipe(ItemManager.woodenRadiusUpgrade, new Object[]{
            "P P", "PCP", "LFL",
            'P', Blocks.PLANKS,
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', ItemManager.core,
            'F', Blocks.REDSTONE_TORCH,
        });

        addRecipe(ItemManager.ironRadiusUpgrade, new Object[]{
            "I I", "IPI", "LFL",
            'I', Items.IRON_INGOT,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.woodenRadiusUpgrade,
            'F', Blocks.REDSTONE_TORCH,
        });

        addRecipe(ItemManager.goldRadiusUpgrade, new Object[]{
            "G G", "GPG", "LFL",
            'G', Items.GOLD_INGOT,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.ironRadiusUpgrade,
            'F', Blocks.REDSTONE_TORCH,
        });

        addRecipe(ItemManager.diamondRadiusUpgrade, new Object[]{
            "D D", "DPD", "LFL",
            'D', Items.DIAMOND,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.goldRadiusUpgrade,
            'F', Blocks.REDSTONE_TORCH,
        });

        addRecipe(ItemManager.poisonModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.SPIDER_EYE,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.fireModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.BLAZE_POWDER,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.lightingModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.BLAZE_ROD,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.witherModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.GHAST_TEAR,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.slowdownModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.STRING,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.blindnessModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.ENDER_EYE,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.confusionModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.FERMENTED_SPIDER_EYE,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.healModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.GOLDEN_CARROT,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.regenerationModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.GOLDEN_APPLE,
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.hungryModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', new ItemStack(Items.FISH, 1, 3),
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.waterBreathingModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', new ItemStack(Items.FISH, 1, 0),
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.weaknessModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', new ItemStack(Items.SKULL, 1, 0),
            'L', new ItemStack(Items.DYE, 1, 4),
            'P', ItemManager.core,
        });*/

        /*
         * Filters
         */
        /*addRecipe(ItemManager.enemyFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', Blocks.STONE,
            'S', Items.BONE,
            'B', Items.ROTTEN_FLESH,
        });

        addRecipe(ItemManager.playerFilter, new Object[]{
            " L ", "ACS", " L ",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', Blocks.STONE,
            'S', ItemManager.selfPlayerFilter,
            'A', ItemManager.anotherPlayerFilter,
        });

        addRecipe(ItemManager.selfPlayerFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', Blocks.STONE,
            'S', Items.WOODEN_SHOVEL,
            'B', Items.WOODEN_AXE,
        });

        addRecipe(ItemManager.anotherPlayerFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', Blocks.STONE,
            'S', Items.WOODEN_HOE,
            'B', Items.WOODEN_SWORD,
        });

        addRecipe(ItemManager.animalFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', Blocks.STONE,
            'S', Blocks.WOOL,
            'B', Items.LEATHER,
        });

        addRecipe(ItemManager.waterFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', Blocks.STONE,
            'S', Items.FISH,
            'B', new ItemStack(Items.DYE, 1, 0),
        });

        addRecipe(ItemManager.slimeFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.DYE, 1, 4),
            'C', Blocks.STONE,
            'S', Items.SLIME_BALL,
            'B', Items.SNOWBALL,
        });*/
    }

    private static void addRecipe(Item item, Object[] recipe) {
        GameRegistry.addRecipe(new ItemStack(item, 1), recipe);
    }

    private static void addRecipe(Block block, Object[] recipe) {
        GameRegistry.addRecipe(new ItemStack(block, 1), recipe);
    }

    private static void addRecipe(ItemStack itemStack, Object[] recipe) {
        GameRegistry.addRecipe(itemStack, recipe);
    }

    private static void addShapelessRecipe(Block block, int amount, Object... recipe) {
        GameRegistry.addShapelessRecipe(new ItemStack(block, amount), recipe);
    }
}
