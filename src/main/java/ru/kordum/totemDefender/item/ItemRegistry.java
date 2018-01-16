package ru.kordum.totemDefender.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.block.BlockRegistry;
import ru.kordum.totemDefender.block.BlockSlab;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.config.Config;
import ru.kordum.totemDefender.item.common.ItemCore;
import ru.kordum.totemDefender.item.common.ItemDoor;
import ru.kordum.totemDefender.item.common.ItemSlab;
import ru.kordum.totemDefender.item.common.ItemTotem;
import ru.kordum.totemDefender.item.upgrade.ItemFilter;
import ru.kordum.totemDefender.item.upgrade.ItemMode;
import ru.kordum.totemDefender.item.upgrade.ItemModifierUpgrade;
import ru.kordum.totemDefender.item.upgrade.ItemUpgrade;

public class ItemRegistry {
    public static Item CORE;
    public static Item DOOR;
    public static Item SLAB;
    public static Item STAIRS;
    public static Item FENCE;
    public static Item FENCE_GATE;
    public static Item PLANKS;
    public static Item LOG;
    public static Item LOG_FACE1;
    public static Item LOG_FACE2;
    public static Item LOG_FACE3;
    public static Item SAPLING;
    public static Item LEAVES;

    public static Item WOODEN_TOTEM;
    public static Item IRON_TOTEM;
    public static Item GOLDEN_TOTEM;
    public static Item DIAMOND_TOTEM;

    public static Item WOODEN_DAMAGE_UPGRADE;
    public static Item WOODEN_AS_UPGRADE;
    public static Item WOODEN_RADIUS_UPGRADE;
    public static Item IRON_DAMAGE_UPGRADE;
    public static Item IRON_AS_UPGRADE;
    public static Item IRON_RADIUS_UPGRADE;
    public static Item GOLDEN_DAMAGE_UPGRADE;
    public static Item GOLDEN_AS_UPGRADE;
    public static Item GOLDEN_RADIUS_UPGRADE;
    public static Item DIAMOND_DAMAGE_UPGRADE;
    public static Item DIAMOND_AS_UPGRADE;
    public static Item DIAMOND_RADIUS_UPGRADE;

    public static Item PLAYER_FILTER;
    public static Item SELF_PLAYER_FILTER;
    public static Item ANOTHER_PLAYER_FILTER;
    public static Item ANIMAL_FILTER;
    public static Item ENEMY_FILTER;
    public static Item SLIME_FILTER;
    public static Item WATER_FILTER;

    public static Item PROJECTILE_MODE;
    public static Item AOE_MODE;

    public static Item FIRE_MODIFIER;
    public static Item POISON_MODIFIER;
    public static Item LIGHTING_MODIFIER;
    public static Item WITHER_MODIFIER;
    public static Item SLOWDOWN_MODIFIER;
    public static Item BLINDNESS_MODIFIER;
    public static Item CONFUSION_MODIFIER;
    public static Item HEAL_MODIFIER;
    public static Item HUNGRY_MODIFIER;
    public static Item REGENERATION_MODIFIER;
    public static Item WATER_BREATHING_MODIFIER;
    public static Item WEAKNESS_MODIFIER;
    public static Item KNOCKBACK_MODIFIER;

    private static List<Item> itemList;

    public static void init(Config config) {
        itemList = new ArrayList<>();
        CORE = prepareItem(new ItemCore(), "core");
        DOOR = prepareItem(new ItemDoor(BlockRegistry.DOOR), "door");
        SLAB = prepareItem(new ItemSlab(BlockRegistry.SLAB, (BlockSlab) BlockRegistry.SLAB, (BlockSlab) BlockRegistry.DOUBLE_SLAB), "slab");
        STAIRS = prepareItem(BlockRegistry.STAIRS);
        FENCE = prepareItem(BlockRegistry.FENCE);
        FENCE_GATE = prepareItem(BlockRegistry.FENCE_GATE);
        PLANKS = prepareItem(BlockRegistry.PLANKS);
        LOG = prepareItem(BlockRegistry.LOG);
        LOG_FACE1 = prepareItem(BlockRegistry.LOG_FACE1);
        LOG_FACE2 = prepareItem(BlockRegistry.LOG_FACE2);
        LOG_FACE3 = prepareItem(BlockRegistry.LOG_FACE3);
        SAPLING = prepareItem(BlockRegistry.SAPLING);
        LEAVES = prepareItem(BlockRegistry.LEAVES);

        WOODEN_TOTEM = prepareItem(new ItemTotem(BlockRegistry.WOODEN_TOTEM));
        IRON_TOTEM = prepareItem(new ItemTotem(BlockRegistry.IRON_TOTEM));
        GOLDEN_TOTEM = prepareItem(new ItemTotem(BlockRegistry.GOLDEN_TOTEM));
        DIAMOND_TOTEM = prepareItem(new ItemTotem(BlockRegistry.DIAMOND_TOTEM));

        WOODEN_AS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_WOODEN, config.woodenASUpgrade), "wooden_as_upgrade");
        WOODEN_DAMAGE_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_WOODEN, config.woodenDamageUpgrade), "wooden_damage_upgrade");
        WOODEN_RADIUS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_WOODEN, config.woodenRadiusUpgrade), "wooden_radius_upgrade");

        IRON_AS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_IRON, config.ironASUpgrade), "iron_as_upgrade");
        IRON_DAMAGE_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_IRON, config.ironDamageUpgrade), "iron_damage_upgrade");
        IRON_RADIUS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_IRON, config.ironRadiusUpgrade), "iron_radius_upgrade");

        GOLDEN_AS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_GOLD, config.goldASUpgrade), "gold_as_upgrade");
        GOLDEN_DAMAGE_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_GOLD, config.goldDamageUpgrade), "gold_damage_upgrade");
        GOLDEN_RADIUS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_GOLD, config.goldRadiusUpgrade), "gold_radius_upgrade");

        DIAMOND_AS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_DIAMOND, config.diamondASUpgrade), "diamond_as_upgrade");
        DIAMOND_DAMAGE_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_DIAMOND, config.diamondDamageUpgrade), "diamond_damage_upgrade");
        DIAMOND_RADIUS_UPGRADE = prepareItem(new ItemUpgrade(BlockTotem.LEVEL_DIAMOND, config.diamondRadiusUpgrade), "diamond_radius_upgrade");

        FIRE_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.FIRE, config.fireModifier), "fire_upgrade");
        POISON_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.POISON, config.poisonModifier), "poison_upgrade");
        LIGHTING_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.LIGHTING, config.lightingModifier), "lighting_upgrade");
        WITHER_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.WITHER, config.witherModifier), "wither_upgrade");
        SLOWDOWN_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.SLOWDOWN, config.slowdownModifier), "slowdown_upgrade");
        BLINDNESS_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.BLINDNESS, config.blindnessModifier), "blindness_upgrade");
        CONFUSION_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.CONFUSION, config.confusionModifier), "confusion_upgrade");
        HEAL_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.HEAL, config.healModifier), "heal_upgrade");
        HUNGRY_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.HUNGRY, config.hungryModifier), "hungry_upgrade");
        REGENERATION_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.REGENERATION, config.regenerationModifier), "regeneration_upgrade");
        WATER_BREATHING_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.WATER_BREATHING, config.waterBreathingModifier), "water_breathing_upgrade");
        WEAKNESS_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.WEAKNESS, config.weaknessModifier), "weakness_upgrade");
        KNOCKBACK_MODIFIER = prepareItem(new ItemModifierUpgrade(ItemModifierUpgrade.KNOCKBACK, config.knockbackModifier), "knockback_upgrade");

        PLAYER_FILTER = prepareItem(new ItemFilter(ItemFilter.PLAYER), "player_filter");
        SELF_PLAYER_FILTER = prepareItem(new ItemFilter(ItemFilter.SELF_PLAYER), "self_player_filter");
        ANOTHER_PLAYER_FILTER = prepareItem(new ItemFilter(ItemFilter.ANOTHER_PLAYER), "another_player_filter");
        ANIMAL_FILTER = prepareItem(new ItemFilter(ItemFilter.ANIMAL), "animal_filter");
        ENEMY_FILTER = prepareItem(new ItemFilter(ItemFilter.ENEMY), "enemy_filter");
        SLIME_FILTER = prepareItem(new ItemFilter(ItemFilter.SLIME), "slime_filter");
        WATER_FILTER = prepareItem(new ItemFilter(ItemFilter.WATER_MOB), "water_filter");

        PROJECTILE_MODE = prepareItem(new ItemMode(ItemMode.PROJECTILE), "projectile_mode");
        AOE_MODE = prepareItem(new ItemMode(ItemMode.AOE), "aoe_mode");
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(itemList.toArray(new Item[0]));
    }

    private static Item prepareItem(Item item, String name) {
        itemList.add(item);
        item.setUnlocalizedName(name);
        item.setRegistryName(name);
        item.setCreativeTab(TotemDefender.tab);
        return item;
    }

    private static Item prepareItem(Block block) {
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        item.setUnlocalizedName(block.getRegistryName().getResourcePath());
        itemList.add(item);
        return item;
    }

    private static Item prepareItem(ItemBlock item) {
        item.setRegistryName(item.getBlock().getRegistryName());
        item.setUnlocalizedName(item.getBlock().getRegistryName().getResourcePath());
        itemList.add(item);
        return item;
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        for (Item item : itemList) {
            registerRender(item);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Item item) {
        ModelResourceLocation resourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, resourceLocation);
    }
}
