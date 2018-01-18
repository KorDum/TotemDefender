package ru.kordum.totemDefender.config;

public class ConfigSapling {
    private double growChance;
    private double bonemealChance;

    public ConfigSapling(double growChance, double bonemealChance) {
        this.growChance = growChance;
        this.bonemealChance = bonemealChance;
    }

    public double getGrowChance() {
        return growChance;
    }

    public double getBonemealChance() {
        return bonemealChance;
    }
}
