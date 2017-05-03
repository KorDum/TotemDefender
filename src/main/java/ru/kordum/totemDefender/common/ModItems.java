package ru.kordum.totemDefender.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.kordum.totemDefender.common.blocks.BlockTotem;
import ru.kordum.totemDefender.common.config.Config;
import ru.kordum.totemDefender.common.items.common.ItemCore;
import ru.kordum.totemDefender.common.items.common.ItemDoor;
import ru.kordum.totemDefender.common.items.common.ItemSlab;
import ru.kordum.totemDefender.common.items.upgrades.ItemFilter;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;
import ru.kordum.totemDefender.common.items.upgrades.ItemModifierUpgrade;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

public class ModItems {
    public static ItemCore core;
    public static ItemDoor door;
    public static ItemSlab slab;
    public static Item stairs;
    public static Item fence;
    public static Item fenceGate;
    public static Item planks;
    public static Item log;
    public static Item logFace1;
    public static Item logFace2;
    public static Item logFace3;
    public static Item sapling;
    public static Item leaf;

    public static Item woodenTotem;
    public static Item ironTotem;
    public static Item goldTotem;
    public static Item diamondTotem;

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
        core = new ItemCore("core");
        door = new ItemDoor("door");
        slab = new ItemSlab("slab", ModBlocks.slab, ModBlocks.slab, ModBlocks.doubleSlab);
        stairs = getItemFromBLock(ModBlocks.stairs);
        fence = getItemFromBLock(ModBlocks.fence);
        fenceGate = getItemFromBLock(ModBlocks.fenceGate);
        planks = getItemFromBLock(ModBlocks.planks);
        log = getItemFromBLock(ModBlocks.log);
        logFace1 = getItemFromBLock(ModBlocks.logFace1);
        logFace2 = getItemFromBLock(ModBlocks.logFace2);
        logFace3 = getItemFromBLock(ModBlocks.logFace3);
        sapling = getItemFromBLock(ModBlocks.sapling);
        leaf = getItemFromBLock(ModBlocks.leaves);

        woodenTotem = getItemFromBLock(ModBlocks.woodenTotem);
        ironTotem = getItemFromBLock(ModBlocks.ironTotem);
        goldTotem = getItemFromBLock(ModBlocks.goldTotem);
        diamondTotem = getItemFromBLock(ModBlocks.diamondTotem);

        woodenASUpgrade = new ItemUpgrade("wooden_as_upgrade", BlockTotem.LEVEL_WOODEN, config.woodenASUpgrade);
        woodenDamageUpgrade = new ItemUpgrade("wooden_damage_upgrade", BlockTotem.LEVEL_WOODEN, config.woodenDamageUpgrade);
        woodenRadiusUpgrade = new ItemUpgrade("wooden_radius_upgrade", BlockTotem.LEVEL_WOODEN, config.woodenRadiusUpgrade);

        ironASUpgrade = new ItemUpgrade("iron_as_upgrade", BlockTotem.LEVEL_IRON, config.ironASUpgrade);
        ironDamageUpgrade = new ItemUpgrade("iron_damage_upgrade", BlockTotem.LEVEL_IRON, config.ironDamageUpgrade);
        ironRadiusUpgrade = new ItemUpgrade("iron_radius_upgrade", BlockTotem.LEVEL_IRON, config.ironRadiusUpgrade);

        goldASUpgrade = new ItemUpgrade("gold_as_upgrade", BlockTotem.LEVEL_GOLD, config.goldASUpgrade);
        goldDamageUpgrade = new ItemUpgrade("gold_damage_upgrade", BlockTotem.LEVEL_GOLD, config.goldDamageUpgrade);
        goldRadiusUpgrade = new ItemUpgrade("gold_radius_upgrade", BlockTotem.LEVEL_GOLD, config.goldRadiusUpgrade);

        diamondASUpgrade = new ItemUpgrade("diamond_as_upgrade", BlockTotem.LEVEL_DIAMOND, config.diamondASUpgrade);
        diamondDamageUpgrade = new ItemUpgrade("diamond_damage_upgrade", BlockTotem.LEVEL_DIAMOND, config.diamondDamageUpgrade);
        diamondRadiusUpgrade = new ItemUpgrade("diamond_radius_upgrade", BlockTotem.LEVEL_DIAMOND, config.diamondRadiusUpgrade);

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

        playerFilter = new ItemFilter("player_filter", ItemFilter.PLAYER);
        selfPlayerFilter = new ItemFilter("self_player_filter", ItemFilter.SELF_PLAYER);
        anotherPlayerFilter = new ItemFilter("another_player_filter", ItemFilter.ANOTHER_PLAYER);
        animalFilter = new ItemFilter("animal_filter", ItemFilter.ANIMAL);
        enemyFilter = new ItemFilter("enemy_filter", ItemFilter.ENEMY);
        slimeFilter = new ItemFilter("slime_filter", ItemFilter.SLIME);
        waterFilter = new ItemFilter("water_filter", ItemFilter.WATER_MOB);

        projectileMode = new ItemMode("projectile_mode", ItemMode.PROJECTILE);
        aoeMode = new ItemMode("aoe_mode", ItemMode.AOE);
    }

    public static void registerRenders() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        registerRender(mesher, core);
        registerRender(mesher, door);

        registerRender(mesher, woodenASUpgrade);
        registerRender(mesher, woodenDamageUpgrade);
        registerRender(mesher, woodenRadiusUpgrade);

        registerRender(mesher, ironASUpgrade);
        registerRender(mesher, ironDamageUpgrade);
        registerRender(mesher, ironRadiusUpgrade);

        registerRender(mesher, goldASUpgrade);
        registerRender(mesher, goldDamageUpgrade);
        registerRender(mesher, goldRadiusUpgrade);

        registerRender(mesher, diamondASUpgrade);
        registerRender(mesher, diamondDamageUpgrade);
        registerRender(mesher, diamondRadiusUpgrade);

        registerRender(mesher, lightingModifier);
        registerRender(mesher, poisonModifier);
        registerRender(mesher, fireModifier);
        registerRender(mesher, witherModifier);
        registerRender(mesher, slowdownModifier);
        registerRender(mesher, blindnessModifier);
        registerRender(mesher, confusionModifier);
        registerRender(mesher, healModifier);
        registerRender(mesher, hungryModifier);
        registerRender(mesher, regenerationModifier);
        registerRender(mesher, waterBreathingModifier);
        registerRender(mesher, weaknessModifier);
        registerRender(mesher, knockbackModifier);

        registerRender(mesher, playerFilter);
        registerRender(mesher, selfPlayerFilter);
        registerRender(mesher, anotherPlayerFilter);
        registerRender(mesher, animalFilter);
        registerRender(mesher, enemyFilter);
        registerRender(mesher, slimeFilter);
        registerRender(mesher, waterFilter);

        registerRender(mesher, projectileMode);
        registerRender(mesher, aoeMode);
    }

    private static void registerRender(ItemModelMesher mesher, Item item) {
        mesher.register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    
    private static Item getItemFromBLock(Block block) {
        Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
        return item;
    }
}
