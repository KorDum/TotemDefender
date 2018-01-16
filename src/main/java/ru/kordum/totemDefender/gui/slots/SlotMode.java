package ru.kordum.totemDefender.gui.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.item.upgrade.ItemMode;

public class SlotMode extends Slot {
    public SlotMode(TileEntityTotem tileEntity, IItemHandler handler, int index, int x, int y) {
        super(tileEntity, handler, index, x, y);
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        tileEntity.updateMode();
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemMode;
    }
}
