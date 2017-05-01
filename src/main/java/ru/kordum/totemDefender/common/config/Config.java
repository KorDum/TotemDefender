package ru.kordum.totemDefender.common.config;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config extends Configuration {
    private static final String TOTEM_CATEGORY = "totem";
    private static final String UPGRADE_CATEGORY = "upgrade";

    private static final String WOODEN_TOTEM_CATEGORY = "woodenTotem";
    private static final String IRON_TOTEM_CATEGORY = "ironTotem";
    private static final String GOLD_TOTEM_CATEGORY = "goldTotem";
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

    private static final String ATTACK_SPEED_PARAM = "attackSpeed";
    private static final String DAMAGE_PARAM = "damage";
    private static final String RADIUS_PARAM = "radius";
    private static final String PERCENT_PARAM = "percent";

    public ConfigTotem woodenTotem;
    public ConfigTotem ironTotem;
    public ConfigTotem goldTotem;
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

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public Config(File file) {
        super(file);
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    private ConfigCategory createDefaultTotemParams(ConfigCategory category, ConfigTotem config) {
        Property attackSpeedProperty = new Property(ATTACK_SPEED_PARAM, String.valueOf(config.getAttackSpeed()), Property.Type.DOUBLE);
        Property damageProperty = new Property(DAMAGE_PARAM, String.valueOf(config.getDamage()), Property.Type.DOUBLE);
        Property radiusProperty = new Property(RADIUS_PARAM, String.valueOf(config.getRadius()), Property.Type.INTEGER);
        category.put(ATTACK_SPEED_PARAM, attackSpeedProperty);
        category.put(DAMAGE_PARAM, damageProperty);
        category.put(RADIUS_PARAM, radiusProperty);
        return category;
    }

    private ConfigCategory createUpgradeParams(ConfigCategory category, ConfigUpgrade config) {
        Property attackSpeedProperty = new Property(ATTACK_SPEED_PARAM, String.valueOf(config.getAttackSpeed()), Property.Type.DOUBLE);
        Property damageProperty = new Property(DAMAGE_PARAM, String.valueOf(config.getDamage()), Property.Type.DOUBLE);
        Property radiusProperty = new Property(RADIUS_PARAM, String.valueOf(config.getRadius()), Property.Type.INTEGER);
        Property percentProperty = new Property(PERCENT_PARAM, String.valueOf(config.isPercent()), Property.Type.BOOLEAN);
        category.put(ATTACK_SPEED_PARAM, attackSpeedProperty);
        category.put(DAMAGE_PARAM, damageProperty);
        category.put(RADIUS_PARAM, radiusProperty);
        category.put(PERCENT_PARAM, percentProperty);
        return category;
    }

    private ConfigTotem createTotemConfig(String subCategory, float defaultSpeed, float defaultDamage,
        int defaultRadius) {
        ConfigCategory category = getCategory(TOTEM_CATEGORY + "." + subCategory);

        if (category.isEmpty()) {
            ConfigTotem totemConfig = new ConfigTotem(defaultSpeed, defaultDamage, defaultRadius);
            createDefaultTotemParams(category, totemConfig);
        }

        float speed = (float) category.get(ATTACK_SPEED_PARAM).getDouble();
        float damage = (float) category.get(DAMAGE_PARAM).getDouble();
        int radius = category.get(RADIUS_PARAM).getInt();
        return new ConfigTotem(speed, damage, radius);
    }

    private ConfigUpgrade createUpgradeConfig(String subCategory, float defaultSpeed, float defaultDamage,
        int defaultRadius, boolean isPercent) {
        ConfigCategory category = getCategory(UPGRADE_CATEGORY + "." + subCategory);

        if (category.isEmpty()) {
            ConfigUpgrade upgradeConfig = new ConfigUpgrade(defaultSpeed, defaultDamage, defaultRadius, isPercent);
            createUpgradeParams(category, upgradeConfig);
            return upgradeConfig;
        }

        float speed = (float) category.get(ATTACK_SPEED_PARAM).getDouble();
        float damage = (float) category.get(DAMAGE_PARAM).getDouble();
        int radius = category.get(RADIUS_PARAM).getInt();
        boolean percent = category.get(PERCENT_PARAM).getBoolean();
        return new ConfigUpgrade(speed, damage, radius, percent);
    }

    private ConfigUpgrade createUpgradeConfig(String subCategory, float defaultSpeed, float defaultDamage,
        int defaultRadius) {
        return createUpgradeConfig(subCategory, defaultSpeed, defaultDamage, defaultRadius, false);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public void loadAndSave() {
        load();

        /**
         * Totems
         */
        woodenTotem = createTotemConfig(WOODEN_TOTEM_CATEGORY, 0.4f, 5, 3);
        ironTotem = createTotemConfig(IRON_TOTEM_CATEGORY, 0.5f, 6, 3);
        goldTotem = createTotemConfig(GOLD_TOTEM_CATEGORY, 0.6f, 7, 4);
        diamondTotem = createTotemConfig(DIAMOND_TOTEM_CATEGORY, 0.8f, 8, 4);

        /**
         * Wooden Upgrades
         */
        woodenASUpgrade = createUpgradeConfig(WOODEN_AS_UPGRADE_CATEGORY, 0.1f, -2, 0);
        woodenDamageUpgrade = createUpgradeConfig(WOODEN_DAMAGE_UPGRADE_CATEGORY, -0.01f, 1, 0);
        woodenRadiusUpgrade = createUpgradeConfig(WOODEN_RADIUS_UPGRADE_CATEGORY, -0.1f, -3, 1);

        /**
         * Iron Upgrades
         */
        ironASUpgrade = createUpgradeConfig(IRON_AS_UPGRADE_CATEGORY, 0.2f, -2, 0);
        ironDamageUpgrade = createUpgradeConfig(IRON_DAMAGE_UPGRADE_CATEGORY, -0.05f, 2, 0);
        ironRadiusUpgrade = createUpgradeConfig(IRON_RADIUS_UPGRADE_CATEGORY, -0.1f, -2, 1);

        /**
         * Gold Upgrades
         */
        goldASUpgrade = createUpgradeConfig(GOLD_AS_UPGRADE_CATEGORY, 0.2f, -1f, 0);
        goldDamageUpgrade = createUpgradeConfig(GOLD_DAMAGE_UPGRADE_CATEGORY, -0.1f, 3, 0);
        goldRadiusUpgrade = createUpgradeConfig(GOLD_RADIUS_UPGRADE_CATEGORY, -0.1f, -1, 2);

        /**
         * Diamond Upgrades
         */
        diamondASUpgrade = createUpgradeConfig(DIAMOND_AS_UPGRADE_CATEGORY, 0.3f, 0, 0);
        diamondDamageUpgrade = createUpgradeConfig(DIAMOND_DAMAGE_UPGRADE_CATEGORY, -0.15f, 4, 0);
        diamondRadiusUpgrade = createUpgradeConfig(DIAMOND_RADIUS_UPGRADE_CATEGORY, -0.1f, 0, 2);

        /**
         * Modifiers
         */
        poisonModifier = createUpgradeConfig(POISON_MODIFIER_CATEGORY, -30, -25, 0, true);
        fireModifier = createUpgradeConfig(FIRE_MODIFIER_CATEGORY, -25, -30, 0, true);
        lightingModifier = createUpgradeConfig(LIGHTING_MODIFIER_CATEGORY, -50, 0, 0, true);
        witherModifier = createUpgradeConfig(WITHER_MODIFIER_CATEGORY, -50, 0, 0, true);
        slowdownModifier = createUpgradeConfig(SLOWDOWN_MODIFIER_CATEGORY, -10, 0, 0, true);
        blindnessModifier = createUpgradeConfig(BLINDNESS_MODIFIER_CATEGORY, -10, 0, 0, true);
        confusionModifier = createUpgradeConfig(CONFUSION_MODIFIER_CATEGORY, -10, 0, 0, true);
        healModifier = createUpgradeConfig(HEAL_MODIFIER_CATEGORY, -50, 0, -25, true);
        hungryModifier = createUpgradeConfig(HUNGRY_MODIFIER_CATEGORY, -10, 0, 0, true);
        regenerationModifier = createUpgradeConfig(REGENERATION_MODIFIER_CATEGORY, -10, 0, 0, true);
        waterBreathingModifier = createUpgradeConfig(WATER_BREATHING_MODIFIER_CATEGORY, -10, 0, 0, true);
        weaknessModifier = createUpgradeConfig(WEAKNESS_MODIFIER_CATEGORY, -10, 0, 0, true);

        save();
    }
}
