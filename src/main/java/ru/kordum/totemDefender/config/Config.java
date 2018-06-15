package ru.kordum.totemDefender.config;

import net.minecraftforge.common.config.Configuration;
import ru.kordum.totemDefender.item.EnumUpgrade;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Config extends Configuration {
    private static final String TOTEM = "totem";
    private static final String MISC = "misc";

    private static final String TOTEM_WOODEN = "woodenTotem";
    private static final String TOTEM_IRON = "ironTotem";
    private static final String TOTEM_GOLDEN = "goldenTotem";
    private static final String TOTEM_DIAMOND = "diamondTotem";

    private static final String UPGRADE_WOODEN_AS = "woodenAttackSpeed";
    private static final String UPGRADE_WOODEN_DAMAGE = "woodenDamage";
    private static final String UPGRADE_WOODEN_RADIUS = "woodenRadius";

    private static final String UPGRADE_IRON_AS = "ironAttackSpeed";
    private static final String UPGRADE_IRON_DAMAGE = "ironDamage";
    private static final String UPGRADE_IRON_RADIUS = "ironRadius";

    private static final String UPGRADE_GOLDEN_AS = "goldAttackSpeed";
    private static final String UPGRADE_GOLDEN_DAMAGE = "goldDamage";
    private static final String UPGRADE_GOLDEN_RADIUS = "goldRadius";

    private static final String UPGRADE_DIAMOND_AS = "diamondAttackSpeed";
    private static final String UPGRADE_DIAMOND_DAMAGE = "diamondDamage";
    private static final String UPGRADE_DIAMOND_RADIUS = "diamondRadius";

    private static final String UPGRADE_POISON = "poison";
    private static final String UPGRADE_FIRE = "fire";
    private static final String UPGRADE_LIGHTING = "lighting";
    private static final String UPGRADE_WITHER = "wither";
    private static final String UPGRADE_SLOWDOWN = "slowdown";
    private static final String UPGRADE_BLINDNESS = "blindness";
    private static final String UPGRADE_CONFUSION = "confusion";
    private static final String UPGRADE_HEAL = "heal";
    private static final String UPGRADE_HUNGRY = "hungry";
    private static final String UPGRADE_REGENERATION = "regeneration";
    private static final String UPGRADE_WATER_BREATHING = "waterBreathing";
    private static final String UPGRADE_WEAKNESS = "weakness";
    private static final String UPGRADE_KNOCKBACK = "knockback";

    public ConfigTotem woodenTotem;
    public ConfigTotem ironTotem;
    public ConfigTotem goldenTotem;
    public ConfigTotem diamondTotem;
    public ConfigSapling sapling;
    public Map<String, ConfigUpgrade> upgrades;

    public Config(File file) {
        super(file);
        woodenTotem = ConfigTotem.parse(this, TOTEM, TOTEM_WOODEN, 0.4f, 5, 3);
        ironTotem = ConfigTotem.parse(this, TOTEM, TOTEM_IRON, 0.5f, 6, 3);
        goldenTotem = ConfigTotem.parse(this, TOTEM, TOTEM_GOLDEN, 0.6f, 7, 4);
        diamondTotem = ConfigTotem.parse(this, TOTEM, TOTEM_DIAMOND, 0.8f, 8, 4);
        sapling = ConfigSapling.parse(this, MISC, 0.05F, 0.05F, 30);

        upgrades = new HashMap<>();
        upgrades.put(
            EnumUpgrade.WOODEN_AS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_WOODEN_AS, 0.1f, false, -2, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.WOODEN_DAMAGE.getName(),
            ConfigUpgrade.parse(this, UPGRADE_WOODEN_DAMAGE, -0.01f, false, 1, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.WOODEN_RADIUS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_WOODEN_RADIUS, -0.1f, false, -3, false, 1, false)
        );

        upgrades.put(
            EnumUpgrade.IRON_AS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_IRON_AS, 0.2f, false, -2, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.IRON_DAMAGE.getName(),
            ConfigUpgrade.parse(this, UPGRADE_IRON_DAMAGE, -0.05f, false, 2, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.IRON_RADIUS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_IRON_RADIUS, -0.1f, false, -2, false, 1, false)
        );

        upgrades.put(
            EnumUpgrade.GOLDEN_AS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_GOLDEN_AS, 0.2f, false, -1f, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.GOLDEN_DAMAGE.getName(),
            ConfigUpgrade.parse(this, UPGRADE_GOLDEN_DAMAGE, -0.1f, false, 3, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.GOLDEN_RADIUS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_GOLDEN_RADIUS, -0.1f, false, -1, false, 2, false)
        );

        upgrades.put(
            EnumUpgrade.DIAMOND_AS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_DIAMOND_AS, 0.3f, false, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.DIAMOND_DAMAGE.getName(),
            ConfigUpgrade.parse(this, UPGRADE_DIAMOND_DAMAGE, -0.15f, false, 4, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.DIAMOND_RADIUS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_DIAMOND_RADIUS, -0.1f, false, 0, false, 2, false)
        );

        upgrades.put(
            EnumUpgrade.POISON.getName(),
            ConfigUpgrade.parse(this, UPGRADE_POISON, -30, true, -25, true, 0, false)
        );
        upgrades.put(
            EnumUpgrade.FIRE.getName(),
            ConfigUpgrade.parse(this, UPGRADE_FIRE, -25, true, -30, true, 0, false)
        );
        upgrades.put(
            EnumUpgrade.LIGHTING.getName(),
            ConfigUpgrade.parse(this, UPGRADE_LIGHTING, -50, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.WITHER.getName(),
            ConfigUpgrade.parse(this, UPGRADE_WITHER, -50, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.SLOWDOWN.getName(),
            ConfigUpgrade.parse(this, UPGRADE_SLOWDOWN, -10, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.BLINDNESS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_BLINDNESS, -10, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.CONFUSION.getName(),
            ConfigUpgrade.parse(this, UPGRADE_CONFUSION, -10, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.HEAL.getName(),
            ConfigUpgrade.parse(this, UPGRADE_HEAL, -50, true, 0, false, -25, true)
        );
        upgrades.put(
            EnumUpgrade.HUNGRY.getName(),
            ConfigUpgrade.parse(this, UPGRADE_HUNGRY, -10, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.REGENERATION.getName(),
            ConfigUpgrade.parse(this, UPGRADE_REGENERATION, -10, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.WATER_BREATHING.getName(),
            ConfigUpgrade.parse(this, UPGRADE_WATER_BREATHING, -10, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.WEAKNESS.getName(),
            ConfigUpgrade.parse(this, UPGRADE_WEAKNESS, -10, true, 0, false, 0, false)
        );
        upgrades.put(
            EnumUpgrade.KNOCKBACK.getName(),
            ConfigUpgrade.parse(this, UPGRADE_KNOCKBACK, -50, true, 0, false, 0, false)
        );
        save();
    }

    public ConfigUpgrade getConfigUpgrade(String key) {
        return upgrades.get(key);
    }
}
