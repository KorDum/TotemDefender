package ru.kordum.totemDefender.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.kordum.totemDefender.TotemDefender;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.model.ICustomRenderModel;
import ru.kordum.totemDefender.util.Formatter;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTotem extends ItemBlock implements ICustomRenderModel {
    public ItemTotem(Block block) {
        super(block);
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

        for (BlockTotem.EnumType type : BlockTotem.EnumType.values()) {
            ItemStack subItem = new ItemStack(this, 1, type.ordinal());
            items.add(subItem);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        BlockTotem.EnumType type = BlockTotem.EnumType.byMeta(meta);
        return super.getUnlocalizedName(stack) + "." + type.getName();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            BlockTotem.EnumType type = BlockTotem.EnumType.byMeta(stack.getMetadata());
            tooltip.add(Formatter.getStat(TextFormatting.BLUE, "prop.attack_speed", type.getAttackSpeed()));
            tooltip.add(Formatter.getStat(TextFormatting.RED, "prop.damage", type.getDamage()));
            tooltip.add(Formatter.getStat(TextFormatting.GREEN, "prop.radius", type.getRadius()));
        } else {
            tooltip.add(Formatter.getLocalize(TextFormatting.GRAY, "prop.hold_more"));
        }
    }

    @Override
    public void registerRender() {
        for (BlockTotem.EnumType type : BlockTotem.EnumType.values()) {
            ModelResourceLocation location = new ModelResourceLocation(this.getRegistryName() + "_" + type.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(this, type.ordinal(), location);
        }
    }
}
