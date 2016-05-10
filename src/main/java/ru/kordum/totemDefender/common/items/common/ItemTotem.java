package ru.kordum.totemDefender.common.items.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
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
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        super.addInformation(itemStack, player, list, par4);
        list.add(EnumChatFormatting.BLUE + Formatter.getProp("prop.attackSpeed", block.getAttackSpeed(), false));
        list.add(EnumChatFormatting.RED + Formatter.getProp("prop.damage", block.getDamage(), false));
        list.add(EnumChatFormatting.GREEN + Formatter.getProp("prop.radius", block.getRadius(), false));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
    }
}
