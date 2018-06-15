package ru.kordum.totemDefender.item.upgrade;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
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

    public enum EnumFilter implements IStringSerializable {
        PLAYER("player"),
        SELF_PLAYER("self_player"),
        ANOTHER_PLAYER("another_player"),
        ANIMAL("animal"),
        ENEMY("enemy"),
        SLIME("slime"),
        WATER("water");

        private final String name;

        EnumFilter(String name) {
            this.name = name;
        }

        public static EnumFilter byMeta(int meta) {
            for (EnumFilter type : EnumFilter.values()) {
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

        @Override
        public String toString() {
            return name;
        }
    }
}
