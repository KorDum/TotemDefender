package ru.kordum.totemDefender.gui.slots;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.item.EnumUpgrade;
import ru.kordum.totemDefender.item.ItemUpgrade;

public class SlotUpgrade extends Slot {
    public SlotUpgrade(TileEntityTotem tileEntity, IItemHandler handler, int index, int x, int y) {
        super(tileEntity, handler, index, x, y);
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        tileEntity.updateState();
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (!(stack.getItem() instanceof ItemUpgrade)) {
            return false;
        }

        EnumUpgrade type = EnumUpgrade.byMeta(stack.getMetadata());
        return type.getLevel() <= tileEntity.getLevel();
    }
}
