package ru.kordum.totemDefender.common.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.kordum.totemDefender.common.items.filters.ItemFilter;

public class SlotFilter extends Slot {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public SlotFilter(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemFilter;
    }
}
