package ru.kordum.totemDefender.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.items.common.ItemCore;
import ru.kordum.totemDefender.common.items.common.ItemDoor;
import ru.kordum.totemDefender.common.items.common.ItemSlab;
import ru.kordum.totemDefender.common.items.upgrades.ItemFilter;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;
import ru.kordum.totemDefender.common.items.upgrades.ItemModifierUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

public class ItemManager {
    public static ItemCore core;
    public static ItemDoor door;
    public static ItemSlab slab;
    public static Item stairs;
    public static Item fence;
    public static Item fenceGate;
    public static Item planks;

    public static ItemUpgrade woodenDamageUpgrade;
    public static ItemUpgrade woodenASUpgrade;
    public static ItemUpgrade woodenRadiusUpgrade;
    public static ItemUpgrade ironDamageUpgrade;
    public static ItemUpgrade ironASUpgrade;
    public static ItemUpgrade ironRadiusUpgrade;
    public static ItemUpgrade goldDamageUpgrade;
    public static ItemUpgrade goldASUpgrade;
    public static ItemUpgrade goldRadiusUpgrade;
    public static ItemUpgrade diamondDamageUpgrade;
    public static ItemUpgrade diamondASUpgrade;
    public static ItemUpgrade diamondRadiusUpgrade;

    public static ItemFilter playerFilter;
    public static ItemFilter selfPlayerFilter;
    public static ItemFilter anotherPlayerFilter;
    public static ItemFilter animalFilter;
    public static ItemFilter enemyFilter;
    public static ItemFilter slimeFilter;
    public static ItemFilter waterFilter;

    public static ItemMode projectileMode;
    public static ItemMode aoeMode;

    public static ItemModifierUpgrade fireModifier;
    public static ItemModifierUpgrade poisonModifier;
    public static ItemModifierUpgrade lightingModifier;
    public static ItemModifierUpgrade witherModifier;
    public static ItemModifierUpgrade slowdownModifier;
    public static ItemModifierUpgrade blindnessModifier;
    public static ItemModifierUpgrade confusionModifier;
    public static ItemModifierUpgrade healModifier;
    public static ItemModifierUpgrade hungryModifier;
    public static ItemModifierUpgrade regenerationModifier;
    public static ItemModifierUpgrade waterBreathingModifier;
    public static ItemModifierUpgrade weaknessModifier;
    public static ItemModifierUpgrade knockbackModifier;

    public static void registerItems(Config config) {
        /*
         * Common
         */
        core = new ItemCore("core");
        door = new ItemDoor("totem_tree_door");
        slab = new ItemSlab("totem_tree_slab", BlockManager.slab, BlockManager.slab, BlockManager.doubleSlab);
        stairs = new ItemBlock(BlockManager.stairs).setRegistryName(BlockManager.stairs.getRegistryName());
        fence = new ItemBlock(BlockManager.fence).setRegistryName(BlockManager.fence.getRegistryName());
        fenceGate = new ItemBlock(BlockManager.fenceGate).setRegistryName(BlockManager.fenceGate.getRegistryName());
        planks = new ItemBlock(BlockManager.planks).setRegistryName(BlockManager.planks.getRegistryName());

        GameRegistry.register(stairs);
        GameRegistry.register(fence);
        GameRegistry.register(fenceGate);
        GameRegistry.register(planks);

        /*
         * Wooden upgrades
         */
        woodenASUpgrade = new ItemUpgrade("wooden_as_upgrade", ItemUpgrade.LEVEL_WOODEN, config.woodenASUpgrade);
        woodenDamageUpgrade = new ItemUpgrade("wooden_damage_upgrade", ItemUpgrade.LEVEL_WOODEN, config.woodenDamageUpgrade);
        woodenRadiusUpgrade = new ItemUpgrade("wooden_radius_upgrade", ItemUpgrade.LEVEL_WOODEN, config.woodenRadiusUpgrade);

        /*
         * Iron Upgrades
         */
        ironASUpgrade = new ItemUpgrade("iron_as_upgrade", ItemUpgrade.LEVEL_IRON, config.ironASUpgrade);
        ironDamageUpgrade = new ItemUpgrade("iron_damage_upgrade", ItemUpgrade.LEVEL_IRON, config.ironDamageUpgrade);
        ironRadiusUpgrade = new ItemUpgrade("iron_radius_upgrade", ItemUpgrade.LEVEL_IRON, config.ironRadiusUpgrade);

        /*
         * Gold Upgrades
         */
        goldASUpgrade = new ItemUpgrade("gold_as_upgrade", ItemUpgrade.LEVEL_GOLD, config.goldASUpgrade);
        goldDamageUpgrade = new ItemUpgrade("gold_damage_upgrade", ItemUpgrade.LEVEL_GOLD, config.goldDamageUpgrade);
        goldRadiusUpgrade = new ItemUpgrade("gold_radius_upgrade", ItemUpgrade.LEVEL_GOLD, config.goldRadiusUpgrade);

        /*
         * Diamond Upgrades
         */
        diamondASUpgrade = new ItemUpgrade("diamond_as_upgrade", ItemUpgrade.LEVEL_DIAMOND, config.diamondASUpgrade);
        diamondDamageUpgrade = new ItemUpgrade("diamond_damage_upgrade", ItemUpgrade.LEVEL_DIAMOND, config.diamondDamageUpgrade);
        diamondRadiusUpgrade = new ItemUpgrade("diamond_radius_upgrade", ItemUpgrade.LEVEL_DIAMOND, config.diamondRadiusUpgrade);

        /*
         * Modifiers
         */
        fireModifier = new ItemModifierUpgrade("fire_upgrade", ItemModifierUpgrade.FIRE, config.fireModifier);
        poisonModifier = new ItemModifierUpgrade("poison_upgrade", ItemModifierUpgrade.POISON, config.poisonModifier);
        lightingModifier = new ItemModifierUpgrade("lighting_upgrade", ItemModifierUpgrade.LIGHTING, config.lightingModifier);
        witherModifier = new ItemModifierUpgrade("wither_upgrade", ItemModifierUpgrade.WITHER, config.witherModifier);
        slowdownModifier = new ItemModifierUpgrade("slowdown_upgrade", ItemModifierUpgrade.SLOWDOWN, config.slowdownModifier);
        blindnessModifier = new ItemModifierUpgrade("blindness_upgrade", ItemModifierUpgrade.BLINDNESS, config.blindnessModifier);
        confusionModifier = new ItemModifierUpgrade("confusion_upgrade", ItemModifierUpgrade.CONFUSION, config.confusionModifier);
        healModifier = new ItemModifierUpgrade("heal_upgrade", ItemModifierUpgrade.HEAL, config.healModifier);
        hungryModifier = new ItemModifierUpgrade("hungry_upgrade", ItemModifierUpgrade.HUNGRY, config.hungryModifier);
        regenerationModifier = new ItemModifierUpgrade("regeneration_upgrade", ItemModifierUpgrade.REGENERATION, config.regenerationModifier);
        waterBreathingModifier = new ItemModifierUpgrade("water_breathing_upgrade", ItemModifierUpgrade.WATER_BREATHING, config.waterBreathingModifier);
        weaknessModifier = new ItemModifierUpgrade("weakness_upgrade", ItemModifierUpgrade.WEAKNESS, config.weaknessModifier);
        knockbackModifier = new ItemModifierUpgrade("knockback_upgrade", ItemModifierUpgrade.KNOCKBACK, config.knockbackModifier);

        /*
         * Filters
         */
        playerFilter = new ItemFilter("player_filter", ItemFilter.PLAYER);
        selfPlayerFilter = new ItemFilter("self_player_filter", ItemFilter.SELF_PLAYER);
        anotherPlayerFilter = new ItemFilter("another_player_filter", ItemFilter.ANOTHER_PLAYER);
        animalFilter = new ItemFilter("animal_filter", ItemFilter.ANIMAL);
        enemyFilter = new ItemFilter("enemy_filter", ItemFilter.ENEMY);
        slimeFilter = new ItemFilter("slime_filter", ItemFilter.SLIME);
        waterFilter = new ItemFilter("water_filter", ItemFilter.WATER_MOB);

        /*
         * Modes
         */
        projectileMode = new ItemMode("projectile_mode", ItemMode.PROJECTILE);
        aoeMode = new ItemMode("aoe_mode", ItemMode.AOE);
    }

    public static void registerMeshes() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        registerMesh(mesher, core);
        registerMesh(mesher, door);

        registerMesh(mesher, woodenASUpgrade);
        registerMesh(mesher, woodenDamageUpgrade);
        registerMesh(mesher, woodenRadiusUpgrade);

        registerMesh(mesher, ironASUpgrade);
        registerMesh(mesher, ironDamageUpgrade);
        registerMesh(mesher, ironRadiusUpgrade);

        registerMesh(mesher, goldASUpgrade);
        registerMesh(mesher, goldDamageUpgrade);
        registerMesh(mesher, goldRadiusUpgrade);

        registerMesh(mesher, diamondASUpgrade);
        registerMesh(mesher, diamondDamageUpgrade);
        registerMesh(mesher, diamondRadiusUpgrade);

        registerMesh(mesher, lightingModifier);
        registerMesh(mesher, poisonModifier);
        registerMesh(mesher, fireModifier);
        registerMesh(mesher, witherModifier);
        registerMesh(mesher, slowdownModifier);
        registerMesh(mesher, blindnessModifier);
        registerMesh(mesher, confusionModifier);
        registerMesh(mesher, healModifier);
        registerMesh(mesher, hungryModifier);
        registerMesh(mesher, regenerationModifier);
        registerMesh(mesher, waterBreathingModifier);
        registerMesh(mesher, weaknessModifier);
        registerMesh(mesher, knockbackModifier);

        registerMesh(mesher, playerFilter);
        registerMesh(mesher, selfPlayerFilter);
        registerMesh(mesher, anotherPlayerFilter);
        registerMesh(mesher, animalFilter);
        registerMesh(mesher, enemyFilter);
        registerMesh(mesher, slimeFilter);
        registerMesh(mesher, waterFilter);

        registerMesh(mesher, projectileMode);
        registerMesh(mesher, aoeMode);
    }

    private static void registerMesh(ItemModelMesher mesher, Item item) {
        mesher.register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
