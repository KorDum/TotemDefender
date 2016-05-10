package ru.kordum.totemDefender.common;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.items.common.ItemCore;
import ru.kordum.totemDefender.common.items.common.ItemDoor;
import ru.kordum.totemDefender.common.items.filters.ItemAnimalFilter;
import ru.kordum.totemDefender.common.items.filters.ItemAnotherPlayerFilter;
import ru.kordum.totemDefender.common.items.filters.ItemEnemyFilter;
import ru.kordum.totemDefender.common.items.filters.ItemLivingFilter;
import ru.kordum.totemDefender.common.items.filters.ItemPlayerFilter;
import ru.kordum.totemDefender.common.items.filters.ItemSelfPlayerFilter;
import ru.kordum.totemDefender.common.items.filters.ItemWaterFilter;
import ru.kordum.totemDefender.common.items.modes.ItemAoeMode;
import ru.kordum.totemDefender.common.items.modes.ItemProjectileMode;
import ru.kordum.totemDefender.common.items.upgrades.ItemBlindnessUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemConfusionUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemDiamondASUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemDiamondDamageUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemDiamondRadiusUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemFireUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemGoldASUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemGoldDamageUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemGoldRadiusUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemHealUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemHungryUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemIronASUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemIronDamageUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemIronRadiusUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemLightingUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemPoisonUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemRegenerationUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemSlowdownUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemWaterBreathingUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemWeaknessUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemWitherUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemWoodenASUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemWoodenDamageUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemWoodenRadiusUpgrade;

public class ItemManager {
    public static ItemCore core;
    public static ItemDoor door;

    public static ItemWoodenDamageUpgrade woodenDamageUpgrade;
    public static ItemWoodenASUpgrade woodenASUpgrade;
    public static ItemWoodenRadiusUpgrade woodenRadiusUpgrade;
    public static ItemIronDamageUpgrade ironDamageUpgrade;
    public static ItemIronASUpgrade ironASUpgrade;
    public static ItemIronRadiusUpgrade ironRadiusUpgrade;
    public static ItemGoldDamageUpgrade goldDamageUpgrade;
    public static ItemGoldASUpgrade goldASUpgrade;
    public static ItemGoldRadiusUpgrade goldRadiusUpgrade;
    public static ItemDiamondDamageUpgrade diamondDamageUpgrade;
    public static ItemDiamondASUpgrade diamondASUpgrade;
    public static ItemDiamondRadiusUpgrade diamondRadiusUpgrade;

    public static ItemPlayerFilter playerFilter;
    public static ItemSelfPlayerFilter selfPlayerFilter;
    public static ItemAnotherPlayerFilter anotherPlayersFilter;
    public static ItemAnimalFilter animalFilter;
    public static ItemEnemyFilter enemyFilter;
    public static ItemLivingFilter livingFilter;
    public static ItemWaterFilter waterFilter;

    public static ItemProjectileMode projectileMode;
    public static ItemAoeMode aoeMode;
    public static ItemFireUpgrade fireModifier;
    public static ItemPoisonUpgrade poisonModifier;
    public static ItemLightingUpgrade lightingModifier;
    public static ItemWitherUpgrade witherModifier;
    public static ItemSlowdownUpgrade slowdownModifier;
    public static ItemBlindnessUpgrade blindnessModifier;
    public static ItemConfusionUpgrade confusionModifier;
    public static ItemHealUpgrade healModifier;
    public static ItemHungryUpgrade hungryModifier;
    public static ItemRegenerationUpgrade regenerationModifier;
    public static ItemWaterBreathingUpgrade waterBreathingModifier;
    public static ItemWeaknessUpgrade weaknessModifier;

    public static void registerItems(Config config) {
        /**
         * Common
         */
        core = new ItemCore();
        door = new ItemDoor();

        /**
         * Wooden upgrades
         */
        woodenASUpgrade = new ItemWoodenASUpgrade(config.woodenASUpgrade);
        woodenDamageUpgrade = new ItemWoodenDamageUpgrade(config.woodenDamageUpgrade);
        woodenRadiusUpgrade = new ItemWoodenRadiusUpgrade(config.woodenRadiusUpgrade);

        /**
         * Iron Upgrades
         */
        ironASUpgrade = new ItemIronASUpgrade(config.ironASUpgrade);
        ironDamageUpgrade = new ItemIronDamageUpgrade(config.ironDamageUpgrade);
        ironRadiusUpgrade = new ItemIronRadiusUpgrade(config.ironRadiusUpgrade);

        /**
         * Gold Upgrades
         */
        goldASUpgrade = new ItemGoldASUpgrade(config.goldASUpgrade);
        goldDamageUpgrade = new ItemGoldDamageUpgrade(config.goldDamageUpgrade);
        goldRadiusUpgrade = new ItemGoldRadiusUpgrade(config.goldRadiusUpgrade);

        /**
         * Diamond Upgrades
         */
        diamondASUpgrade = new ItemDiamondASUpgrade(config.diamondASUpgrade);
        diamondDamageUpgrade = new ItemDiamondDamageUpgrade(config.diamondDamageUpgrade);
        diamondRadiusUpgrade = new ItemDiamondRadiusUpgrade(config.diamondRadiusUpgrade);

        /**
         * Modifiers
         */
        fireModifier = new ItemFireUpgrade(config.fireModifier);
        poisonModifier = new ItemPoisonUpgrade(config.poisonModifier);
        lightingModifier = new ItemLightingUpgrade(config.lightingModifier);
        witherModifier = new ItemWitherUpgrade(config.witherModifier);
        slowdownModifier = new ItemSlowdownUpgrade(config.slowdownModifier);
        blindnessModifier = new ItemBlindnessUpgrade(config.blindnessModifier);
        confusionModifier = new ItemConfusionUpgrade(config.confusionModifier);
        healModifier = new ItemHealUpgrade(config.healModifier);
        hungryModifier = new ItemHungryUpgrade(config.hungryModifier);
        regenerationModifier = new ItemRegenerationUpgrade(config.regenerationModifier);
        waterBreathingModifier = new ItemWaterBreathingUpgrade(config.waterBreathingModifier);
        weaknessModifier = new ItemWeaknessUpgrade(config.weaknessModifier);

        /**
         * Filters
         */
        playerFilter = new ItemPlayerFilter();
        selfPlayerFilter = new ItemSelfPlayerFilter();
        anotherPlayersFilter = new ItemAnotherPlayerFilter();
        animalFilter = new ItemAnimalFilter();
        enemyFilter = new ItemEnemyFilter();
        livingFilter = new ItemLivingFilter();
        waterFilter = new ItemWaterFilter();

        /**
         * Modes
         */
        projectileMode = new ItemProjectileMode();
        aoeMode = new ItemAoeMode();

        GameRegistry.registerItem(door, door.getName());
    }
}
