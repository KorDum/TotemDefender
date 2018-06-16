package ru.kordum.totemDefender.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.model.ICustomRenderModel;
import ru.kordum.totemDefender.util.Formatter;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUpgrade extends Item implements ICustomRenderModel {
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_4 = 4;

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

        for (EnumUpgrade type : EnumUpgrade.values()) {
            ItemStack subItem = new ItemStack(this, 1, type.ordinal());
            items.add(subItem);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        EnumUpgrade type = EnumUpgrade.byMeta(meta);
        return super.getUnlocalizedName(stack) + "." + type.getName();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            EnumUpgrade type = EnumUpgrade.byMeta(stack.getMetadata());
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
        for (EnumUpgrade type : EnumUpgrade.values()) {
            ModelResourceLocation location = new ModelResourceLocation(this.getRegistryName() + "_" + type.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(this, type.ordinal(), location);
        }
    }
}
