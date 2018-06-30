package ru.kordum.totemDefender;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.handler.ItemRegistry;

import javax.annotation.Nonnull;

public class ModCreativeTab extends CreativeTabs {
    public ModCreativeTab() {
        super(CreativeTabs.getNextID(), "tab_name");
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemRegistry.TOTEM);
    }
}
