package ru.kordum.totemDefender.common.items.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.input.Keyboard;
import ru.kordum.totemDefender.common.blocks.BlockTotem;
import ru.kordum.totemDefender.common.utils.Formatter;

import java.util.List;

public class ItemTotem extends ItemBlock {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ItemTotem(Block block) {
        super(block);
        setRegistryName(block.getRegistryName());
        GameRegistry.register(this);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (advanced || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            BlockTotem block = (BlockTotem) this.block;
            tooltip.add(Formatter.getProp(TextFormatting.BLUE, "prop.attack_speed", block.getAttackSpeed(), false));
            tooltip.add(Formatter.getProp(TextFormatting.RED, "prop.damage", block.getDamage(), false));
            tooltip.add(Formatter.getProp(TextFormatting.GREEN, "prop.radius", block.getRadius(), false));
        } else {
            tooltip.add(Formatter.getLocalize(TextFormatting.GRAY, "prop.hold_more"));
        }
    }
}
