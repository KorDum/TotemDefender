package ru.kordum.totemDefender.config;

public class ConfigSapling {
    private static final String CATEGORY = "sapling";

    private static final String PARAM_GROW_CHANCE = "growChance";
    private static final String PARAM_BONEMEAL_CHANCE = "bonemealChance";
    private static final String PARAM_SAPLING_DROP_CHANCE = "dropChance";

    private double growChance;
    private double bonemealChance;
    private int dropChance;

    public ConfigSapling(double growChance, double bonemealChance, int dropChance) {
        this.growChance = growChance;
        this.bonemealChance = bonemealChance;
        this.dropChance = dropChance;
    }

    public double getGrowChance() {
        return growChance;
    }

    public double getBonemealChance() {
        return bonemealChance;
    }

    public int getDropChance() {
        return dropChance;
    }

    public static ConfigSapling parse(Config config, String category, float growChance, float bonemealChance, int dropChance) {
        category += "." + CATEGORY;
        growChance = config.getFloat(PARAM_GROW_CHANCE, category, growChance, 0, 1, "Chance grow tree naturally");
        bonemealChance = config.getFloat(PARAM_BONEMEAL_CHANCE, category, bonemealChance, 0, 1, "Chance grow tree with Bonemeal");
        dropChance = config.getInt(PARAM_SAPLING_DROP_CHANCE, category, dropChance, 1, Short.MAX_VALUE, "Chance drop sapling from tree leaf. The smaller, the more often! Be careful! Vanilla is 20");
        return new ConfigSapling(growChance, bonemealChance, dropChance);
    }
}
