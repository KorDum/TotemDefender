package ru.kordum.totemDefender.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import ru.kordum.totemDefender.common.ResourceManager;
import ru.kordum.totemDefender.common.entities.TileEntityTotem;
import ru.kordum.totemDefender.common.gui.ContainerTotem;

import java.text.NumberFormat;

@SideOnly(Side.CLIENT)
public class GuiTotem extends GuiContainer {
    protected TileEntityTotem tileEntity;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public GuiTotem(ContainerTotem container) {
        super(container);
        tileEntity = container.getTileEntity();
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE HANDLERS
    //
    //---------------------------------------------------------------------------

    private void drawString(String string, int x, int y) {
        fontRendererObj.drawString(string, x, y, 0x404040);
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        fontRendererObj.drawString(tileEntity.getInventoryName(), 8, 6, 0x404040);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 0x404040);

        drawString(getFormattedProp("gui.attackSpeed", tileEntity.getAttackSpeed()), 22, 26);
        drawString(getFormattedProp("gui.damage", tileEntity.getDamage()), 67, 26);
        drawString(getFormattedProp("gui.radius", tileEntity.getRadius()), 112, 26);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(ResourceManager.getGuiTotem());
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

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
        return StatCollector.translateToLocalFormatted(key, nf.format(value));
    }

    private String getFormattedProp(String key, int value) {
        String valueStr = String.valueOf(value);
        return StatCollector.translateToLocalFormatted(key, valueStr);
    }
}
