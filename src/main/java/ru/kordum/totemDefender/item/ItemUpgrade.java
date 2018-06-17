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

    @SideOnly(Side.CLIENT)
    @Override
    public void registerRender() {
        for (EnumType type : ItemUpgrade.EnumType.values()) {
            ModelResourceLocation location = new ModelResourceLocation(this.getRegistryName() + "_" + type.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(this, type.ordinal(), location);
        }
    }

    public enum EnumType implements IStringSerializable {
        WOODEN_AS(BlockTotem.EnumType.LEVEL_1),
        WOODEN_DAMAGE(BlockTotem.EnumType.LEVEL_1),
        WOODEN_RADIUS(BlockTotem.EnumType.LEVEL_1),

        IRON_AS(BlockTotem.EnumType.LEVEL_2),
        IRON_DAMAGE(BlockTotem.EnumType.LEVEL_2),
        IRON_RADIUS(BlockTotem.EnumType.LEVEL_2),

        GOLDEN_AS(BlockTotem.EnumType.LEVEL_3),
        GOLDEN_DAMAGE(BlockTotem.EnumType.LEVEL_3),
        GOLDEN_RADIUS(BlockTotem.EnumType.LEVEL_3),

        DIAMOND_AS(BlockTotem.EnumType.LEVEL_4),
        DIAMOND_DAMAGE(BlockTotem.EnumType.LEVEL_4),
        DIAMOND_RADIUS(BlockTotem.EnumType.LEVEL_4),

        POISON(BlockTotem.EnumType.LEVEL_1),
        FIRE(BlockTotem.EnumType.LEVEL_1),
        LIGHTING(BlockTotem.EnumType.LEVEL_1),
        WITHER(BlockTotem.EnumType.LEVEL_1),
        SLOWDOWN(BlockTotem.EnumType.LEVEL_1),
        BLINDNESS(BlockTotem.EnumType.LEVEL_1),
        CONFUSION(BlockTotem.EnumType.LEVEL_1),
        HEAL(BlockTotem.EnumType.LEVEL_1),
        HUNGRY(BlockTotem.EnumType.LEVEL_1),
        REGENERATION(BlockTotem.EnumType.LEVEL_1),
        WATER_BREATHING(BlockTotem.EnumType.LEVEL_1),
        WEAKNESS(BlockTotem.EnumType.LEVEL_1),
        KNOCKBACK(BlockTotem.EnumType.LEVEL_1);

        private final String name;
        private final int level;
        private final int flag;
        private ConfigUpgrade config;

        EnumType(int level) {
            name = name().toLowerCase();
            this.level = level;
            flag = (int) Math.pow(ordinal(), 2);
        }

        public static EnumType byMeta(int meta) {
            for (EnumType type : values()) {
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

        public int getFlag() {
            return flag;
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

        public boolean check(short modifier) {
            return (modifier & flag) == flag;
        }
    }
}
