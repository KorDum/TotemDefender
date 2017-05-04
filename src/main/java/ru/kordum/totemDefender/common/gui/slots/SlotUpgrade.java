package ru.kordum.totemDefender.common.gui.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

public class SlotUpgrade extends Slot {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public SlotUpgrade(TileEntityTotem tileEntity, IItemHandler handler, int index, int x, int y) {
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
        tileEntity.updateState();
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (!(stack.getItem() instanceof ItemUpgrade)) {
            return false;
        }

        ItemUpgrade item = (ItemUpgrade) stack.getItem();
        return item.getLevel() <= tileEntity.getLevel();
    }

    
}