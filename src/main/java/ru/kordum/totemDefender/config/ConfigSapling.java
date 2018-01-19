package ru.kordum.totemDefender.config;

public class ConfigSapling {
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
}
