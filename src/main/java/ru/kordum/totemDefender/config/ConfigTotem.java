package ru.kordum.totemDefender.config;

public class ConfigTotem {
    private float attackSpeed;
    private float damage;
    private int radius;

    public ConfigTotem(float attackSpeed, float damage, int radius) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.radius = radius;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getDamage() {
        return damage;
    }

    public int getRadius() {
        return radius;
    }
}
