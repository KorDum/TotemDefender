package ru.kordum.totemDefender.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;
import ru.kordum.totemDefender.common.gui.slots.SlotFilter;
import ru.kordum.totemDefender.common.gui.slots.SlotMode;
import ru.kordum.totemDefender.common.gui.slots.SlotUpgrade;

public abstract class ContainerTotem extends Container {
    protected TileEntityTotem tileEntity;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public ContainerTotem(InventoryPlayer inventoryPlayer, TileEntityTotem tileEntity) {
        this.tileEntity = tileEntity;
        createTotemInventory();
        bindPlayerInventory(inventoryPlayer);
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    protected void createTotemInventory() {
        int filterSlotCount = tileEntity.getFilterSlotCount();
        int upgradeSlotCount = tileEntity.getUpgradeSlotCount();
        addSlotToContainer(new SlotMode(tileEntity, 0, 152, 19));

        for (int i = 1; i <= filterSlotCount; i++) {
            addSlotToContainer(new SlotFilter(tileEntity, i, 8 + (i - 1) * 18, 53));
        }

        for (int i = 1 + filterSlotCount; i <= filterSlotCount + upgradeSlotCount; i++) {
            addSlotToContainer(new SlotUpgrade(tileEntity, i, 98 + (i - filterSlotCount - 1) * 18, 53));
        }
    }

    protected void bindPlayerInventory(InventoryPlayer player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
    }

    private void transferFromPlayer(int slot, ItemStack stackInSlot) {
        for (int i = 0; i < tileEntity.getSizeInventory(); i++) {
            if (!tileEntity.hasStackInSlot(i)) {
                Slot targetSlot = getSlot(i);

                if (targetSlot.isItemValid(stackInSlot)) {
                    if (stackInSlot.stackSize == 1) {
                        targetSlot.putStack(stackInSlot);
                        putStackInSlot(slot, null);
                    } else {
                        putStackInSlot(i, stackInSlot.splitStack(1));
                    }
                    return;
                }
            }
        }
    }

    private void transferFromTotem(int slot, ItemStack stackInSlot) {
        for (int i = tileEntity.getSizeInventory(); i < tileEntity.getSizeInventory() + 36; i++) {
            Slot targetSlot = getSlot(i);
            ItemStack stackInTargetSlot = targetSlot.getStack();

            if (stackInTargetSlot == null ||
                (stackInTargetSlot.isItemEqual(stackInSlot) && stackInTargetSlot.stackSize < 64)) {
                putStackInSlot(slot, null);

                if (stackInTargetSlot == null) {
                    putStackInSlot(i, stackInSlot);
                } else {
                    stackInTargetSlot.stackSize++;
                }
                return;
            }
        }
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        Slot slotObject = getSlot(slot);
        ItemStack stackInSlot = slotObject.getStack();

        if (stackInSlot == null) {
            return null;
        }

        // выбран слот из инвентаря турели
        if (slot < tileEntity.getSizeInventory()) {
            transferFromTotem(slot, stackInSlot);
        }
        // выбран слот из инвентаря игрока
        else {
            transferFromPlayer(slot, stackInSlot);
        }

        return null;
    }

    //---------------------------------------------------------------------------
    //
    // ACCESSORS
    //
    //---------------------------------------------------------------------------

    public TileEntityTotem getTileEntity() {
        return tileEntity;
    }
}
