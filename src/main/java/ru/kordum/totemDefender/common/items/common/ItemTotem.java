package ru.kordum.totemDefender.common.items.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import ru.kordum.totemDefender.common.blocks.BlockTotem;
import ru.kordum.totemDefender.common.utils.Formatter;

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
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(itemStack, player, tooltip, advanced);
        if (advanced || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(EnumChatFormatting.BLUE + Formatter.getProp("prop.attackSpeed", block.getAttackSpeed(), false));
            tooltip.add(EnumChatFormatting.RED + Formatter.getProp("prop.damage", block.getDamage(), false));
            tooltip.add(EnumChatFormatting.GREEN + Formatter.getProp("prop.radius", block.getRadius(), false));
        } else {
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("prop.holdMore"));
        }
    }
}
