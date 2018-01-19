package ru.kordum.totemDefender.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config extends Configuration {
    private static final String TOTEM_CATEGORY = "totem";
    private static final String UPGRADE_CATEGORY = "upgrade";
    private static final String MISC_CATEGORY = "misc";

    private static final String SAPLING_CATEGORY = "sapling";

    private static final String WOODEN_TOTEM_CATEGORY = "woodenTotem";
    private static final String IRON_TOTEM_CATEGORY = "ironTotem";
    private static final String GOLDEN_TOTEM_CATEGORY = "goldenTotem";
    private static final String DIAMOND_TOTEM_CATEGORY = "diamondTotem";

    private static final String WOODEN_AS_UPGRADE_CATEGORY = "woodenASUpgrade";
    private static final String WOODEN_DAMAGE_UPGRADE_CATEGORY = "woodenDamageUpgrade";
    private static final String WOODEN_RADIUS_UPGRADE_CATEGORY = "woodenRadiusUpgrade";

    private static final String IRON_AS_UPGRADE_CATEGORY = "ironASUpgrade";
    private static final String IRON_DAMAGE_UPGRADE_CATEGORY = "ironDamageUpgrade";
    private static final String IRON_RADIUS_UPGRADE_CATEGORY = "ironRadiusUpgrade";

    private static final String GOLD_AS_UPGRADE_CATEGORY = "goldASUpgrade";
    private static final String GOLD_DAMAGE_UPGRADE_CATEGORY = "goldDamageUpgrade";
    private static final String GOLD_RADIUS_UPGRADE_CATEGORY = "goldRadiusUpgrade";

    private static final String DIAMOND_AS_UPGRADE_CATEGORY = "diamondASUpgrade";
    private static final String DIAMOND_DAMAGE_UPGRADE_CATEGORY = "diamondDamageUpgrade";
    private static final String DIAMOND_RADIUS_UPGRADE_CATEGORY = "diamondRadiusUpgrade";

    private static final String POISON_MODIFIER_CATEGORY = "poisonModifier";
    private static final String FIRE_MODIFIER_CATEGORY = "fireModifier";
    private static final String LIGHTING_MODIFIER_CATEGORY = "lightingModifier";
    private static final String WITHER_MODIFIER_CATEGORY = "witherModifier";
    private static final String SLOWDOWN_MODIFIER_CATEGORY = "slowdownModifier";
    private static final String BLINDNESS_MODIFIER_CATEGORY = "blindnessModifier";
    private static final String CONFUSION_MODIFIER_CATEGORY = "confusionModifier";
    private static final String HEAL_MODIFIER_CATEGORY = "healModifier";
    private static final String HUNGRY_MODIFIER_CATEGORY = "hungryModifier";
    private static final String REGENERATION_MODIFIER_CATEGORY = "regenerationModifier";
    private static final String WATER_BREATHING_MODIFIER_CATEGORY = "waterBreathingModifier";
    private static final String WEAKNESS_MODIFIER_CATEGORY = "weaknessModifier";
    private static final String KNOCKBACK_MODIFIER_CATEGORY = "knockbackModifier";

    private static final String ATTACK_SPEED_PARAM = "attackSpeed";
    private static final String DAMAGE_PARAM = "damage";
    private static final String RADIUS_PARAM = "radius";
    private static final String ATTACK_SPEED_PERCENT_PARAM = "attackSpeedPercent";
    private static final String DAMAGE_PERCENT_PARAM = "damagePercent";
    private static final String RADIUS_PERCENT_PARAM = "radiusPercent";

    private static final String GROW_CHANCE_PARAM = "growChance";
    private static final String BONEMEAL_CHANCE_PARAM = "bonemealChance";
    private static final String SAPLING_DROP_CHANCE_PARAM = "dropChance";

    public ConfigTotem woodenTotem;
    public ConfigTotem ironTotem;
    public ConfigTotem goldenTotem;
    public ConfigTotem diamondTotem;

    public ConfigUpgrade woodenASUpgrade;
    public ConfigUpgrade woodenDamageUpgrade;
    public ConfigUpgrade woodenRadiusUpgrade;

    public ConfigUpgrade ironASUpgrade;
    public ConfigUpgrade ironDamageUpgrade;
    public ConfigUpgrade ironRadiusUpgrade;

    public ConfigUpgrade goldASUpgrade;
    public ConfigUpgrade goldDamageUpgrade;
    public ConfigUpgrade goldRadiusUpgrade;

    public ConfigUpgrade diamondASUpgrade;
    public ConfigUpgrade diamondDamageUpgrade;
    public ConfigUpgrade diamondRadiusUpgrade;

    public ConfigUpgrade poisonModifier;
    public ConfigUpgrade fireModifier;
    public ConfigUpgrade lightingModifier;
    public ConfigUpgrade witherModifier;
    public ConfigUpgrade slowdownModifier;
    public ConfigUpgrade blindnessModifier;
    public ConfigUpgrade confusionModifier;
    public ConfigUpgrade healModifier;
    public ConfigUpgrade hungryModifier;
    public ConfigUpgrade regenerationModifier;
    public ConfigUpgrade waterBreathingModifier;
    public ConfigUpgrade weaknessModifier;
    public ConfigUpgrade knockbackModifier;

    public ConfigSapling sapling;

    public Config(File file) {
        super(file);
        woodenTotem = createTotemConfig(WOODEN_TOTEM_CATEGORY, 0.4f, 5, 3);
        ironTotem = createTotemConfig(IRON_TOTEM_CATEGORY, 0.5f, 6, 3);
        goldenTotem = createTotemConfig(GOLDEN_TOTEM_CATEGORY, 0.6f, 7, 4);
        diamondTotem = createTotemConfig(DIAMOND_TOTEM_CATEGORY, 0.8f, 8, 4);

        woodenASUpgrade = createUpgradeConfig(WOODEN_AS_UPGRADE_CATEGORY, 0.1f, false, -2, false, 0, false);
        woodenDamageUpgrade = createUpgradeConfig(WOODEN_DAMAGE_UPGRADE_CATEGORY, -0.01f, false, 1, false, 0, false);
        woodenRadiusUpgrade = createUpgradeConfig(WOODEN_RADIUS_UPGRADE_CATEGORY, -0.1f, false, -3, false, 1, false);

        ironASUpgrade = createUpgradeConfig(IRON_AS_UPGRADE_CATEGORY, 0.2f, false, -2, false, 0, false);
        ironDamageUpgrade = createUpgradeConfig(IRON_DAMAGE_UPGRADE_CATEGORY, -0.05f, false, 2, false, 0, false);
        ironRadiusUpgrade = createUpgradeConfig(IRON_RADIUS_UPGRADE_CATEGORY, -0.1f, false, -2, false, 1, false);

        goldASUpgrade = createUpgradeConfig(GOLD_AS_UPGRADE_CATEGORY, 0.2f, false, -1f, false, 0, false);
        goldDamageUpgrade = createUpgradeConfig(GOLD_DAMAGE_UPGRADE_CATEGORY, -0.1f, false, 3, false, 0, false);
        goldRadiusUpgrade = createUpgradeConfig(GOLD_RADIUS_UPGRADE_CATEGORY, -0.1f, false, -1, false, 2, false);

        diamondASUpgrade = createUpgradeConfig(DIAMOND_AS_UPGRADE_CATEGORY, 0.3f, false, 0, false, 0, false);
        diamondDamageUpgrade = createUpgradeConfig(DIAMOND_DAMAGE_UPGRADE_CATEGORY, -0.15f, false, 4, false, 0, false);
        diamondRadiusUpgrade = createUpgradeConfig(DIAMOND_RADIUS_UPGRADE_CATEGORY, -0.1f, false, 0, false, 2, false);

        poisonModifier = createUpgradeConfig(POISON_MODIFIER_CATEGORY, -30, true, -25, true, 0, false);
        fireModifier = createUpgradeConfig(FIRE_MODIFIER_CATEGORY, -25, true, -30, true, 0, false);
        lightingModifier = createUpgradeConfig(LIGHTING_MODIFIER_CATEGORY, -50, true, 0, false, 0, false);
        witherModifier = createUpgradeConfig(WITHER_MODIFIER_CATEGORY, -50, true, 0, false, 0, false);
        slowdownModifier = createUpgradeConfig(SLOWDOWN_MODIFIER_CATEGORY, -10, true, 0, false, 0, false);
        blindnessModifier = createUpgradeConfig(BLINDNESS_MODIFIER_CATEGORY, -10, true, 0, false, 0, false);
        confusionModifier = createUpgradeConfig(CONFUSION_MODIFIER_CATEGORY, -10, true, 0, false, 0, false);
        healModifier = createUpgradeConfig(HEAL_MODIFIER_CATEGORY, -50, true, 0, false, -25, true);
        hungryModifier = createUpgradeConfig(HUNGRY_MODIFIER_CATEGORY, -10, true, 0, false, 0, false);
        regenerationModifier = createUpgradeConfig(REGENERATION_MODIFIER_CATEGORY, -10, true, 0, false, 0, false);
        waterBreathingModifier = createUpgradeConfig(WATER_BREATHING_MODIFIER_CATEGORY, -10, true, 0, false, 0, false);
        weaknessModifier = createUpgradeConfig(WEAKNESS_MODIFIER_CATEGORY, -10, true, 0, false, 0, false);
        knockbackModifier = createUpgradeConfig(KNOCKBACK_MODIFIER_CATEGORY, -50, true, 0, false, 0, false);

        sapling = createSaplingConfig(0.05F, 0.05F, 30);
        save();
    }

    private ConfigTotem createTotemConfig(String subCategory, float speed, float damage, int radius) {
        speed = getFloat(ATTACK_SPEED_PARAM, TOTEM_CATEGORY + "." + subCategory, speed, 0, 3, "Attack speed. The higher the value, the faster");
        damage = getFloat(DAMAGE_PARAM, TOTEM_CATEGORY + "." + subCategory, damage, 0, 1000, "Amount of hitpoints for one shot");
        radius = getInt(RADIUS_PARAM, TOTEM_CATEGORY + "." + subCategory, radius, 0, 16, "Amount of radius in blocks for one shot");
        return new ConfigTotem(speed, damage, radius);
    }

    private ConfigUpgrade createUpgradeConfig(String subCategory, float speed, boolean speedPercent, float damage, boolean damagePercent, int radius, boolean radiusPercent) {
        speed = getFloat(ATTACK_SPEED_PARAM, UPGRADE_CATEGORY + "." + subCategory, speed, -100, 100, "Attack speed. The higher the value, the faster");
        speedPercent = getBoolean(ATTACK_SPEED_PERCENT_PARAM, UPGRADE_CATEGORY + "." + subCategory, speedPercent, "Value as percent");
        damage = getFloat(DAMAGE_PARAM, UPGRADE_CATEGORY + "." + subCategory, damage, -100, 1000, "Amount of hitpoints for one shot");
        damagePercent = getBoolean(DAMAGE_PERCENT_PARAM, UPGRADE_CATEGORY + "." + subCategory, damagePercent, "Value as percent");
        radius = getInt(RADIUS_PARAM, UPGRADE_CATEGORY + "." + subCategory, radius, -100, 100, "Amount of radius in blocks for one shot");
        radiusPercent = getBoolean(RADIUS_PERCENT_PARAM, UPGRADE_CATEGORY + "." + subCategory, radiusPercent, "Value as percent");
        return new ConfigUpgrade(speed, speedPercent, damage, damagePercent, radius, radiusPercent);
    }

    private ConfigSapling createSaplingConfig(float growChance, float bonemealChance, int dropChance) {
        growChance = getFloat(GROW_CHANCE_PARAM, MISC_CATEGORY + "." + SAPLING_CATEGORY, growChance, 0, 1, "Chance grow tree naturally");
        bonemealChance = getFloat(BONEMEAL_CHANCE_PARAM, MISC_CATEGORY + "." + SAPLING_CATEGORY, bonemealChance, 0, 1, "Chance grow tree with Bonemeal");
        dropChance = getInt(SAPLING_DROP_CHANCE_PARAM, MISC_CATEGORY + "." + SAPLING_CATEGORY, dropChance, 1, Short.MAX_VALUE, "Chance drop sapling from tree leaf. The smaller, the more often! Be careful! Vanilla is 20");
        return new ConfigSapling(growChance, bonemealChance, dropChance);
    }
}
