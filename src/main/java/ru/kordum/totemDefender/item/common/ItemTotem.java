package ru.kordum.totemDefender.item.common;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.util.Formatter;

public class ItemTotem extends ItemBlock {
    public ItemTotem(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (flagIn.isAdvanced() || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            BlockTotem block = (BlockTotem) this.block;
            tooltip.add(Formatter.getStat(TextFormatting.BLUE, "prop.attack_speed", block.getAttackSpeed()));
            tooltip.add(Formatter.getStat(TextFormatting.RED, "prop.damage", block.getDamage()));
            tooltip.add(Formatter.getStat(TextFormatting.GREEN, "prop.radius", block.getRadius()));
        } else {
            tooltip.add(Formatter.getLocalize(TextFormatting.GRAY, "prop.hold_more"));
        }
    }
}
