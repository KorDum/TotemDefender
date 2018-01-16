package ru.kordum.totemDefender.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.text.NumberFormat;

import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.util.Formatter;
import ru.kordum.totemDefender.util.ModResources;

@SideOnly(Side.CLIENT)
public class GuiTotem extends GuiContainer {
    protected TileEntityTotem tileEntity;

    public GuiTotem(IInventory playerInv, TileEntityTotem tileEntity) {
        super(new ContainerTotem(playerInv, tileEntity));
        this.tileEntity = tileEntity;
        xSize = 176;
        ySize = 166;
    }

    private void drawString(String string, int x, int y) {
        fontRenderer.drawString(string, x, y, 0x404040);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(tileEntity.getName(), 8, 6, 0x404040);
        fontRenderer.drawString(Formatter.getLocalize("container.inventory"), 8, ySize - 96 + 2, 0x404040);

        drawString(getFormattedProp("gui.attack_speed", tileEntity.getAttackSpeed()), 22, 26);
        drawString(getFormattedProp("gui.damage", tileEntity.getDamage()), 67, 26);
        drawString(getFormattedProp("gui.radius", tileEntity.getRadius()), 112, 26);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(ModResources.getResource(ModResources.CATEGORY_GUI, "gui_totem.png"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        for (int i = tileEntity.getFilterSlotCount(); i < 4; i++) {
            drawTexturedModalRect(7 + x + 18 * i, y + 52, xSize, 0, 18, 18);
        }

        for (int i = tileEntity.getUpgradeSlotCount(); i < 4; i++) {
            drawTexturedModalRect(97 + x + 18 * i, y + 52, xSize, 0, 18, 18);
        }
    }

    private String getFormattedProp(String key, float value) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return Formatter.getLocalize(key, nf.format(value));
    }

    private String getFormattedProp(String key, int value) {
        String valueStr = String.valueOf(value);
        return Formatter.getLocalize(key, valueStr);
    }
}
