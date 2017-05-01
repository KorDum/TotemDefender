package ru.kordum.totemDefender.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.items.ItemBase;
import ru.kordum.totemDefender.common.items.common.ItemCore;
import ru.kordum.totemDefender.common.items.common.ItemDoor;
import ru.kordum.totemDefender.common.items.upgrades.ItemFilter;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;
import ru.kordum.totemDefender.common.items.upgrades.ItemModifierUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

public class ItemManager {
    public static ItemCore core;
    public static ItemDoor door;

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
        anotherPlayerFilter = new ItemFilter("anotherPlayerFilter", ItemFilter.ANOTHER_PLAYER);
        animalFilter = new ItemFilter("animalFilter", ItemFilter.ANIMAL);
        enemyFilter = new ItemFilter("enemyFilter", ItemFilter.ENEMY);
        slimeFilter = new ItemFilter("slimeFilter", ItemFilter.SLIME);
        waterFilter = new ItemFilter("waterFilter", ItemFilter.WATER_MOB);

        /*
         * Modes
         */
        projectileMode = new ItemMode("projectileMode", ItemMode.PROJECTILE);
        aoeMode = new ItemMode("aoeMode", ItemMode.AOE);
    }

    public static void registerMeshes() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        registerMesh(mesher, core);
        registerMesh(mesher, door, door.getName());

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

    private static void registerMesh(ItemModelMesher mesher, ItemBase item) {
        registerMesh(mesher, item, item.getName());
    }

    private static void registerMesh(ItemModelMesher mesher, Item item, String name) {
        mesher.register(item, 0, new ModelResourceLocation(TotemDefender.MODID + ":" + name, "inventory"));
    }
}
