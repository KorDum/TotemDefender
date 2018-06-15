package ru.kordum.totemDefender.config;

public class ConfigUpgrade {
    private static final String CATEGORY = "upgrade";

    private static final String PARAM_ATTACK_SPEED = "attackSpeed";
    private static final String PARAM_DAMAGE = "damage";
    private static final String PARAM_RADIUS = "radius";
    private static final String PARAM_ATTACK_SPEED_PERCENT = "attackSpeedPercent";
    private static final String PARAM_DAMAGE_PERCENT = "damagePercent";
    private static final String PARAM_RADIUS_PERCENT = "radiusPercent";

    private float attackSpeed;
    private float damage;
    private int radius;
    private boolean speedPercent;
    private boolean damagePercent;
    private boolean radiusPercent;

    public ConfigUpgrade(float attackSpeed, boolean speedPercent, float damage, boolean damagePercent, int radius, boolean radiusPercent) {
        this.attackSpeed = attackSpeed;
        this.damage = damage;
        this.radius = radius;
        this.speedPercent = speedPercent;
        this.damagePercent = damagePercent;
        this.radiusPercent = radiusPercent;
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

    public boolean isSpeedPercent() {
        return speedPercent;
    }

    public boolean isDamagePercent() {
        return damagePercent;
    }

    public boolean isRadiusPercent() {
        return radiusPercent;
    }

    public static ConfigUpgrade parse(Config config, String subCategory, float speed, boolean speedPercent, float damage, boolean damagePercent, int radius, boolean radiusPercent) {
        String category = CATEGORY + "." + subCategory;
        speed = config.getFloat(PARAM_ATTACK_SPEED, category, speed, -100, 100, "Attack speed. The higher the value, the faster");
        speedPercent = config.getBoolean(PARAM_ATTACK_SPEED_PERCENT, category, speedPercent, "Value as percent");
        damage = config.getFloat(PARAM_DAMAGE, category, damage, -100, 1000, "Amount of hitpoints for one shot");
        damagePercent = config.getBoolean(PARAM_DAMAGE_PERCENT, category, damagePercent, "Value as percent");
        radius = config.getInt(PARAM_RADIUS, category, radius, -100, 100, "Amount of radius in blocks for one shot");
        radiusPercent = config.getBoolean(PARAM_RADIUS_PERCENT, category, radiusPercent, "Value as percent");
        return new ConfigUpgrade(speed, speedPercent, damage, damagePercent, radius, radiusPercent);
    }
}
