package ru.kordum.totemDefender.config;

public class ConfigTotem {
    private static final String PARAM_ATTACK_SPEED = "attackSpeed";
    private static final String PARAM_DAMAGE = "damage";
    private static final String PARAM_RADIUS = "radius";

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

    public static ConfigTotem parse(Config config, String category, String subCategory, float speed, float damage, int radius) {
        category += "." + subCategory;
        speed = config.getFloat(PARAM_ATTACK_SPEED, category, speed, 0, 3, "Attack speed. Higher value faster");
        damage = config.getFloat(PARAM_DAMAGE, category, damage, 0, 1000, "Amount of hitpoints for one shot");
        radius = config.getInt(PARAM_RADIUS, category, radius, 0, 16, "Amount of radius in blocks for one shot");
        return new ConfigTotem(speed, damage, radius);
    }
}
