package ru.kordum.totemDefender.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.TotemDefender;

public class ItemFilter extends Item {
    public ItemFilter() {
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

        for (EnumFilter type : EnumFilter.values()) {
            ItemStack subItem = new ItemStack(this, 1, type.ordinal());
            items.add(subItem);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        EnumFilter type = EnumFilter.byMeta(meta);
        return super.getUnlocalizedName(stack) + "." + type.getName();
    }
}
