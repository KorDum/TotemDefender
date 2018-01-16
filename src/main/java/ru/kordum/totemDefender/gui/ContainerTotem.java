package ru.kordum.totemDefender.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.gui.slots.SlotFilter;
import ru.kordum.totemDefender.gui.slots.SlotMode;
import ru.kordum.totemDefender.gui.slots.SlotUpgrade;

public class ContainerTotem extends Container {
    private TileEntityTotem tileEntity;
    private IItemHandler handler;

    public ContainerTotem(IInventory playerInv, TileEntityTotem tileEntity) {
        this.tileEntity = tileEntity;
        handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        createTotemInventory();
        bindPlayerInventory(playerInv);
    }

    protected void createTotemInventory() {
        int filterSlotCount = tileEntity.getFilterSlotCount();
        int upgradeSlotCount = tileEntity.getUpgradeSlotCount();
        addSlotToContainer(new SlotMode(tileEntity, handler, 0, 152, 19));

        for (int i = 1; i <= filterSlotCount; i++) {
            addSlotToContainer(new SlotFilter(tileEntity, handler, i, 8 + (i - 1) * 18, 53));
        }
        for (int i = 1 + filterSlotCount; i <= filterSlotCount + upgradeSlotCount; i++) {
            addSlotToContainer(new SlotUpgrade(tileEntity, handler, i, 98 + (i - filterSlotCount - 1) * 18, 53));
        }
    }

    protected void bindPlayerInventory(IInventory inventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(fromSlot);
        if (slot == null || !slot.getHasStack()) {
            return previous;
        }

        ItemStack current = slot.getStack();
        previous = current.copy();

        if (fromSlot < handler.getSlots()) {
            // From the block inventory to player's inventory
            if (!mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // From the player's inventory to block's inventory
            if (!mergeItemStack(current, 0, handler.getSlots(), false)) {
                return ItemStack.EMPTY;
            }
        }

        if (current.getCount() == 0) {
            slot.putStack(ItemStack.EMPTY);
        } else {
            slot.onSlotChanged();
        }

        if (current.getCount() == previous.getCount()) {
            return null;
        }

        slot.onTake(player, current);
        return previous;
    }
}
