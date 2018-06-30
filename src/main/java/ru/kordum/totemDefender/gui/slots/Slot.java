package ru.kordum.totemDefender.gui.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import ru.kordum.totemDefender.tileEntity.TileEntityTotem;

import javax.annotation.Nonnull;

public class Slot extends SlotItemHandler {
    protected TileEntityTotem tileEntity;

    public Slot(TileEntityTotem tileEntity, IItemHandler handler, int index, int x, int y) {
        super(handler, index, x, y);
        this.tileEntity = tileEntity;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return 1;
    }
}
