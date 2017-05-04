package ru.kordum.totemDefender.common.gui.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;
import ru.kordum.totemDefender.common.items.upgrades.ItemMode;

public class SlotMode extends Slot {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public SlotMode(TileEntityTotem tileEntity, IItemHandler handler, int index, int x, int y) {
        super(tileEntity, handler, index, x, y);
    }

    //---------------------------------------------------------------------------
    //
    // HANDLERS
    //
    //---------------------------------------------------------------------------

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        tileEntity.updateMode();
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemMode;
    }
}