package ru.kordum.totemDefender.config;

public class ConfigUpgrade {
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
}
