package ru.kordum.totemDefender.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.config.ConfigUpgrade;
import ru.kordum.totemDefender.model.ICustomRenderModel;
import ru.kordum.totemDefender.util.Formatter;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUpgrade extends Item implements ICustomRenderModel {
    public ItemUpgrade() {
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        super.getSubItems(tab, items);
        if (tab != TotemDefender.tab) {
            return;
        }

        for (EnumType type : ItemUpgrade.EnumType.values()) {
            ItemStack subItem = new ItemStack(this, 1, type.ordinal());
            items.add(subItem);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        EnumType type = ItemUpgrade.EnumType.byMeta(meta);
        return super.getUnlocalizedName(stack) + "." + type.getName();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            EnumType type = ItemUpgrade.EnumType.byMeta(stack.getMetadata());
            float attackSpeed = type.getAttackSpeed();
            if (attackSpeed != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.BLUE, "prop.attack_speed", attackSpeed, type.isAttackSpeedPercent()));
            }

            float damage = type.getDamage();
            if (damage != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.RED, "prop.damage", damage, type.isDamagePercent()));
            }

            int radius = type.getRadius();
            if (radius != 0) {
                tooltip.add(Formatter.getProp(TextFormatting.GREEN, "prop.radius", radius, type.isRadiusPercent()));
            }
        } else {
            tooltip.add(Formatter.getLocalize(TextFormatting.GRAY, "prop.hold_more"));
        }
    }

    @Override
    public void registerRender() {
        for (EnumType type : ItemUpgrade.EnumType.values()) {
            ModelResourceLocation location = new ModelResourceLocation(this.getRegistryName() + "_" + type.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(this, type.ordinal(), location);
        }
    }

    public enum EnumType implements IStringSerializable {
        WOODEN_AS("wooden_as", BlockTotem.EnumType.LEVEL_1),
        WOODEN_DAMAGE("wooden_damage", BlockTotem.EnumType.LEVEL_1),
        WOODEN_RADIUS("wooden_radius", BlockTotem.EnumType.LEVEL_1),

        IRON_AS("iron_as", BlockTotem.EnumType.LEVEL_2),
        IRON_DAMAGE("iron_damage", BlockTotem.EnumType.LEVEL_2),
        IRON_RADIUS("iron_radius", BlockTotem.EnumType.LEVEL_2),

        GOLDEN_AS("golden_as", BlockTotem.EnumType.LEVEL_3),
        GOLDEN_DAMAGE("golden_damage", BlockTotem.EnumType.LEVEL_3),
        GOLDEN_RADIUS("golden_radius", BlockTotem.EnumType.LEVEL_3),

        DIAMOND_AS("diamond_as", BlockTotem.EnumType.LEVEL_4),
        DIAMOND_DAMAGE("diamond_damage", BlockTotem.EnumType.LEVEL_4),
        DIAMOND_RADIUS("diamond_radius", BlockTotem.EnumType.LEVEL_4),

        POISON("poison", BlockTotem.EnumType.LEVEL_1),
        FIRE("fire", BlockTotem.EnumType.LEVEL_1),
        LIGHTING("lighting", BlockTotem.EnumType.LEVEL_1),
        WITHER("wither", BlockTotem.EnumType.LEVEL_1),
        SLOWDOWN("slowdown", BlockTotem.EnumType.LEVEL_1),
        BLINDNESS("blindness", BlockTotem.EnumType.LEVEL_1),
        CONFUSION("confusion", BlockTotem.EnumType.LEVEL_1),
        HEAL("heal", BlockTotem.EnumType.LEVEL_1),
        HUNGRY("hungry", BlockTotem.EnumType.LEVEL_1),
        REGENERATION("regeneration", BlockTotem.EnumType.LEVEL_1),
        WATER_BREATHING("water_breathing", BlockTotem.EnumType.LEVEL_1),
        WEAKNESS("weakness", BlockTotem.EnumType.LEVEL_1),
        KNOCKBACK("knockback", BlockTotem.EnumType.LEVEL_1);

        private String name;
        private int level;
        private ConfigUpgrade config;

        EnumType(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public static EnumType byMeta(int meta) {
            for (EnumType type : ItemUpgrade.EnumType.values()) {
                if (type.ordinal() == meta) {
                    return type;
                }
            }
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public float getAttackSpeed() {
            return config.getAttackSpeed();
        }

        public float getDamage() {
            return config.getDamage();
        }

        public int getRadius() {
            return config.getRadius();
        }

        public boolean isAttackSpeedPercent() {
            return config.isSpeedPercent();
        }

        public boolean isDamagePercent() {
            return config.isDamagePercent();
        }

        public boolean isRadiusPercent() {
            return config.isRadiusPercent();
        }

        public void setConfig(ConfigUpgrade config) {
            this.config = config;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
