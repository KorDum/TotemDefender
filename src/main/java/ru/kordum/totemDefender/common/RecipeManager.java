package ru.kordum.totemDefender.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeManager {
    public static void registerRecipes() {
        OreDictionary.registerOre("plankWood", BlockManager.planks);
        GameRegistry.addSmelting(BlockManager.log, new ItemStack(Items.coal, 1, 1), 0.1f);
        GameRegistry.addSmelting(BlockManager.face1Log, new ItemStack(Items.coal, 1, 1), 1.0f);
        GameRegistry.addSmelting(BlockManager.face2Log, new ItemStack(Items.coal, 1, 1), 1.0f);
        GameRegistry.addSmelting(BlockManager.face3Log, new ItemStack(Items.coal, 1, 1), 1.0f);
        GameRegistry.addSmelting(BlockManager.woodenTotem, new ItemStack(Items.coal, 2, 1), 5.0f);
        GameRegistry.addSmelting(BlockManager.ironTotem, new ItemStack(Items.iron_ingot, 4, 1), 5.0f);
        GameRegistry.addSmelting(BlockManager.goldTotem, new ItemStack(Items.gold_ingot, 4, 1), 5.0f);
        GameRegistry.addSmelting(BlockManager.diamondTotem, new ItemStack(Items.diamond, 4, 1), 5.0f);

        /*
         * Common
         */
        addRecipe(ItemManager.core, new Object[]{
            "L L", "LEL", "LRL",
            'L', new ItemStack(Items.dye, 1, 4),
            'E', Items.ender_pearl,
            'R', Blocks.redstone_block,
        });

        addRecipe(BlockManager.sapling, new Object[]{
            "RRR", "RSR", "RRR",
            'S', new ItemStack(Blocks.sapling, 1, 1),
            'R', Items.redstone,
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
            "PP",
            "PP",
            "PP",
            'P', BlockManager.planks,
        });

        addShapelessRecipe(BlockManager.planks, 4, BlockManager.log);
        addShapelessRecipe(BlockManager.planks, 4, BlockManager.face1Log);
        addShapelessRecipe(BlockManager.planks, 4, BlockManager.face2Log);
        addShapelessRecipe(BlockManager.planks, 4, BlockManager.face3Log);

        /*
         * Totems
         */
        addRecipe(BlockManager.woodenTotem, new Object[]{
            "WLW", "WCW", "WRW",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', ItemManager.core,
            'R', Blocks.redstone_block,
            'W', BlockManager.log,
        });

        addRecipe(BlockManager.ironTotem, new Object[]{
            "ILI", "WCW", "IRI",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', BlockManager.woodenTotem,
            'R', Blocks.redstone_block,
            'W', BlockManager.log,
            'I', Items.iron_ingot,
        });

        addRecipe(BlockManager.goldTotem, new Object[]{
            "ILI", "WCW", "IRI",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', BlockManager.ironTotem,
            'R', Blocks.redstone_block,
            'W', BlockManager.log,
            'I', Items.gold_ingot,
        });

        addRecipe(BlockManager.diamondTotem, new Object[]{
            "ILI", "WCW", "IRI",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', BlockManager.goldTotem,
            'R', Blocks.redstone_block,
            'W', BlockManager.log,
            'I', Items.diamond,
        });

        /*
         * Modes
         */
        addRecipe(ItemManager.projectileMode, new Object[]{
            "PPP", "PCP", "PPP",
            'P', Blocks.stone,
            'C', ItemManager.core,
        });

        addRecipe(ItemManager.aoeMode, new Object[]{
            "PSP", "SCS", "PSP",
            'P', ItemManager.projectileMode,
            'S', Blocks.stone,
            'C', ItemManager.core,
        });

        /*
         * AS Upgrades
         */
        addRecipe(ItemManager.woodenASUpgrade, new Object[]{
            "P P", "PCP", "LFL",
            'P', Blocks.planks,
            'L', new ItemStack(Items.dye, 1, 4),
            'C', ItemManager.core,
            'F', Items.feather,
        });

        addRecipe(ItemManager.ironASUpgrade, new Object[]{
            "I I", "IPI", "LFL",
            'I', Items.iron_ingot,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.woodenASUpgrade,
            'F', Items.feather,
        });

        addRecipe(ItemManager.goldASUpgrade, new Object[]{
            "G G", "GPG", "LFL",
            'G', Items.gold_ingot,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.ironASUpgrade,
            'F', Items.feather,
        });

        addRecipe(ItemManager.diamondASUpgrade, new Object[]{
            "D D", "DPD", "LFL",
            'D', Items.diamond,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.goldASUpgrade,
            'F', Items.feather,
        });

        /*
         * Damage Upgrades
         */
        addRecipe(ItemManager.woodenDamageUpgrade, new Object[]{
            "P P", "PCP", "LFL",
            'P', Blocks.planks,
            'L', new ItemStack(Items.dye, 1, 4),
            'C', ItemManager.core,
            'F', Items.arrow,
        });

        addRecipe(ItemManager.ironDamageUpgrade, new Object[]{
            "I I", "IPI", "LFL",
            'I', Items.iron_ingot,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.woodenDamageUpgrade,
            'F', Items.arrow,
        });

        addRecipe(ItemManager.goldDamageUpgrade, new Object[]{
            "G G", "GPG", "LFL",
            'G', Items.gold_ingot,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.ironDamageUpgrade,
            'F', Items.arrow,
        });

        addRecipe(ItemManager.diamondDamageUpgrade, new Object[]{
            "D D", "DPD", "LFL",
            'D', Items.diamond,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.goldDamageUpgrade,
            'F', Items.arrow,
        });

        /*
         * Radius Upgrades
         */
        addRecipe(ItemManager.woodenRadiusUpgrade, new Object[]{
            "P P", "PCP", "LFL",
            'P', Blocks.planks,
            'L', new ItemStack(Items.dye, 1, 4),
            'C', ItemManager.core,
            'F', Blocks.redstone_torch,
        });

        addRecipe(ItemManager.ironRadiusUpgrade, new Object[]{
            "I I", "IPI", "LFL",
            'I', Items.iron_ingot,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.woodenRadiusUpgrade,
            'F', Blocks.redstone_torch,
        });

        addRecipe(ItemManager.goldRadiusUpgrade, new Object[]{
            "G G", "GPG", "LFL",
            'G', Items.gold_ingot,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.ironRadiusUpgrade,
            'F', Blocks.redstone_torch,
        });

        addRecipe(ItemManager.diamondRadiusUpgrade, new Object[]{
            "D D", "DPD", "LFL",
            'D', Items.diamond,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.goldRadiusUpgrade,
            'F', Blocks.redstone_torch,
        });

        addRecipe(ItemManager.poisonModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.spider_eye,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.fireModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.blaze_powder,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.lightingModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.blaze_rod,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.witherModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.ghast_tear,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.slowdownModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.string,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.blindnessModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.ender_eye,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.confusionModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.fermented_spider_eye,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.healModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.golden_carrot,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.regenerationModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', Items.golden_apple,
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.hungryModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', new ItemStack(Items.fish, 1, 3),
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.waterBreathingModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', new ItemStack(Items.fish, 1, 0),
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.weaknessModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', new ItemStack(Items.skull, 1, 0),
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        addRecipe(ItemManager.knockbackModifier, new Object[]{
            "D D", "DPD", "LDL",
            'D', new ItemStack(Items.feather, 1, 0),
            'L', new ItemStack(Items.dye, 1, 4),
            'P', ItemManager.core,
        });

        /*
         * Filters
         */
        addRecipe(ItemManager.enemyFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', Blocks.stone,
            'S', Items.bone,
            'B', Items.rotten_flesh,
        });

        addRecipe(ItemManager.playerFilter, new Object[]{
            " L ", "ACS", " L ",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', Blocks.stone,
            'S', ItemManager.selfPlayerFilter,
            'A', ItemManager.anotherPlayersFilter,
        });

        addRecipe(ItemManager.selfPlayerFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', Blocks.stone,
            'S', Items.wooden_shovel,
            'B', Items.wooden_axe,
        });

        addRecipe(ItemManager.anotherPlayersFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', Blocks.stone,
            'S', Items.wooden_hoe,
            'B', Items.wooden_sword,
        });

        addRecipe(ItemManager.animalFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', Blocks.stone,
            'S', Blocks.wool,
            'B', Items.leather,
        });

        addRecipe(ItemManager.waterFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', Blocks.stone,
            'S', Items.fish,
            'B', new ItemStack(Items.dye, 1, 0),
        });

        addRecipe(ItemManager.livingFilter, new Object[]{
            " L ", "BCS", " L ",
            'L', new ItemStack(Items.dye, 1, 4),
            'C', Blocks.stone,
            'S', Items.slime_ball,
            'B', Items.snowball,
        });
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
