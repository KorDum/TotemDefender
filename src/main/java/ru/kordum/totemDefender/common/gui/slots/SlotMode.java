package ru.kordum.totemDefender.common.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;

public class SlotMode extends Slot {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public SlotMode(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemMode;
    }
}
