package ru.kordum.totemDefender.gui.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.item.ItemFilter;

public class SlotFilter extends Slot {
    public SlotFilter(TileEntityTotem tileEntity, IItemHandler handler, int index, int x, int y) {
        super(tileEntity, handler, index, x, y);
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        tileEntity.updateFilter();
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemFilter;
    }
}
