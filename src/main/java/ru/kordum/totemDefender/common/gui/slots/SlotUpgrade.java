package ru.kordum.totemDefender.common.gui.slots;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;
import ru.kordum.totemDefender.common.items.upgrades.ItemUpgrade;

public class SlotUpgrade extends Slot {
    private TileEntityTotem tileEntity;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public SlotUpgrade(TileEntityTotem tileEntity, int index, int x, int y) {
        super(tileEntity, index, x, y);
        this.tileEntity = tileEntity;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof ItemUpgrade)) {
            return false;
        }

        ItemUpgrade item = (ItemUpgrade) itemStack.getItem();
        return item.getLevel() <= tileEntity.getLevel();
    }
}
