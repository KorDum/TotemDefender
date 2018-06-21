package ru.kordum.totemDefender.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.util.Formatter;
import ru.kordum.totemDefender.util.ModResources;

import java.text.NumberFormat;

@SideOnly(Side.CLIENT)
public class GuiTotem extends GuiContainer {
    private static final int MAX_FILTER_SLOT = 4;
    private static final int MAX_UPGRADE_SLOT = 4;

    private TileEntityTotem tileEntity;

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
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = "gui.totem." + tileEntity.getType().getName();
        fontRenderer.drawString(Formatter.getLocalize(name), 8, 6, 0x404040);
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

        BlockTotem.EnumType type = tileEntity.getType();
        for (int i = type.getFilterSlots(); i < MAX_FILTER_SLOT; i++) {
            drawTexturedModalRect(7 + x + 18 * i, y + 52, xSize, 0, 18, 18);
        }

        for (int i = type.getUpgradeSlots(); i < MAX_UPGRADE_SLOT; i++) {
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
