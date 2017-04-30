package ru.kordum.totemDefender.common;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.items.common.ItemCore;
import ru.kordum.totemDefender.common.items.common.ItemDoor;
import ru.kordum.totemDefender.common.items.upgrades.ItemFilter;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;
import ru.kordum.totemDefender.common.items.upgrades.ItemModifierUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

public class ItemManager {
    public static ItemCore core;
    public static ItemDoor door;

    public static ItemMode projectileMode;
    public static ItemMode aoeMode;

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
    public static ItemFilter anotherPlayersFilter;
    public static ItemFilter animalFilter;
    public static ItemFilter enemyFilter;
    public static ItemFilter livingFilter;
    public static ItemFilter waterFilter;

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
        core = new ItemCore();
        door = new ItemDoor();

        /*
         * Wooden upgrades
         */
        woodenASUpgrade = new ItemUpgrade("woodenASUpgrade", ItemUpgrade.LEVEL_WOODEN, config.woodenASUpgrade);
        woodenDamageUpgrade = new ItemUpgrade("woodenDamageUpgrade", ItemUpgrade.LEVEL_WOODEN, config.woodenDamageUpgrade);
        woodenRadiusUpgrade = new ItemUpgrade("woodenRadiusUpgrade", ItemUpgrade.LEVEL_WOODEN, config.woodenRadiusUpgrade);

        /*
         * Iron Upgrades
         */
        ironASUpgrade = new ItemUpgrade("ironASUpgrade", ItemUpgrade.LEVEL_IRON, config.ironASUpgrade);
        ironDamageUpgrade = new ItemUpgrade("ironDamageUpgrade", ItemUpgrade.LEVEL_IRON, config.ironDamageUpgrade);
        ironRadiusUpgrade = new ItemUpgrade("ironRadiusUpgrade", ItemUpgrade.LEVEL_IRON, config.ironRadiusUpgrade);

        /*
         * Gold Upgrades
         */
        goldASUpgrade = new ItemUpgrade("goldASUpgrade", ItemUpgrade.LEVEL_GOLD, config.goldASUpgrade);
        goldDamageUpgrade = new ItemUpgrade("goldDamageUpgrade", ItemUpgrade.LEVEL_GOLD, config.goldDamageUpgrade);
        goldRadiusUpgrade = new ItemUpgrade("goldRadiusUpgrade", ItemUpgrade.LEVEL_GOLD, config.goldRadiusUpgrade);

        /*
         * Diamond Upgrades
         */
        diamondASUpgrade = new ItemUpgrade("diamondASUpgrade", ItemUpgrade.LEVEL_DIAMOND, config.diamondASUpgrade);
        diamondDamageUpgrade = new ItemUpgrade("diamondDamageUpgrade", ItemUpgrade.LEVEL_DIAMOND, config.diamondDamageUpgrade);
        diamondRadiusUpgrade = new ItemUpgrade("diamondRadiusUpgrade", ItemUpgrade.LEVEL_DIAMOND, config.diamondRadiusUpgrade);

        /*
         * Modifiers
         */
        fireModifier = new ItemModifierUpgrade("fireUpgrade", ItemModifierUpgrade.FIRE, config.fireModifier);
        poisonModifier = new ItemModifierUpgrade("poisonUpgrade", ItemModifierUpgrade.POISON, config.poisonModifier);
        lightingModifier = new ItemModifierUpgrade("lightingUpgrade", ItemModifierUpgrade.LIGHTING, config.lightingModifier);
        witherModifier = new ItemModifierUpgrade("witherUpgrade", ItemModifierUpgrade.WITHER, config.witherModifier);
        slowdownModifier = new ItemModifierUpgrade("slowdownUpgrade", ItemModifierUpgrade.SLOWDOWN, config.slowdownModifier);
        blindnessModifier = new ItemModifierUpgrade("blindnessUpgrade", ItemModifierUpgrade.BLINDNESS, config.blindnessModifier);
        confusionModifier = new ItemModifierUpgrade("confusionUpgrade", ItemModifierUpgrade.CONFUSION, config.confusionModifier);
        healModifier = new ItemModifierUpgrade("healUpgrade", ItemModifierUpgrade.HEAL, config.healModifier);
        hungryModifier = new ItemModifierUpgrade("hungryUpgrade", ItemModifierUpgrade.HUNGRY, config.hungryModifier);
        regenerationModifier = new ItemModifierUpgrade("regenerationUpgrade", ItemModifierUpgrade.REGENERATION, config.regenerationModifier);
        waterBreathingModifier = new ItemModifierUpgrade("waterBreathingUpgrade", ItemModifierUpgrade.WATER_BREATHING, config.waterBreathingModifier);
        weaknessModifier = new ItemModifierUpgrade("weaknessUpgrade", ItemModifierUpgrade.WEAKNESS, config.weaknessModifier);
        knockbackModifier = new ItemModifierUpgrade("knockbackUpgrade", ItemModifierUpgrade.KNOCKBACK, config.knockbackModifier);

        /*
         * Filters
         */
        playerFilter = new ItemFilter("playerFilter", ItemFilter.PLAYER);
        selfPlayerFilter = new ItemFilter("selfPlayerFilter", ItemFilter.SELF_PLAYER);
        anotherPlayersFilter = new ItemFilter("anotherPlayerFilter", ItemFilter.ANOTHER_PLAYER);
        animalFilter = new ItemFilter("animalFilter", ItemFilter.ANIMAL);
        enemyFilter = new ItemFilter("enemyFilter", ItemFilter.ENEMY);
        livingFilter = new ItemFilter("livingFilter", ItemFilter.SLIME);
        waterFilter = new ItemFilter("waterFilter", ItemFilter.WATER_MOB);

        /*
         * Modes
         */
        projectileMode = new ItemMode("projectileMode", ItemMode.PROJECTILE);
        aoeMode = new ItemMode("aoeMode", ItemMode.AOE);

        GameRegistry.registerItem(door, door.getName());
    }
}
