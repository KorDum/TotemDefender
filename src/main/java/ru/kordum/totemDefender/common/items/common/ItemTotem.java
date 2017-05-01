package ru.kordum.totemDefender.common.items.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import ru.kordum.totemDefender.common.blocks.BlockTotem;

import java.text.NumberFormat;
import java.util.List;

public class ItemTotem extends ItemBlock {
    private BlockTotem block;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemTotem(Block block) {
        super(block);
        this.block = (BlockTotem) block;
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    private String getFormattedProp(String key, float value) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        return StatCollector.translateToLocalFormatted(
            key,
            ((value >= 0) ? "+" : "") + nf.format(value)
        );
    }

    private String getFormattedProp(String key, int value) {
        return StatCollector.translateToLocalFormatted(
            key,
            ((value >= 0) ? "+" : "") + String.valueOf(value)
        );
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        super.addInformation(itemStack, player, list, par4);
        list.add(EnumChatFormatting.BLUE + getFormattedProp("prop.attackSpeed", block.getAttackSpeed()));
        list.add(EnumChatFormatting.RED + getFormattedProp("prop.damage", block.getDamage()));
        list.add(EnumChatFormatting.GREEN + getFormattedProp("prop.radius", block.getRadius()));
    }
}
