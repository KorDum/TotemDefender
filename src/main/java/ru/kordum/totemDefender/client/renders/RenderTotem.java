package ru.kordum.totemDefender.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.kordum.totemDefender.common.ResourceManager;
import ru.kordum.totemDefender.common.entities.TileEntityGoldTotem;
import ru.kordum.totemDefender.common.entities.TileEntityIronTotem;
import ru.kordum.totemDefender.common.entities.TileEntityWoodenTotem;
import ru.kordum.totemDefender.common.models.ModelTotem;

public class RenderTotem extends TileEntitySpecialRenderer {
    private static ResourceLocation TEXTURE_WOODEN;
    private static ResourceLocation TEXTURE_IRON;
    private static ResourceLocation TEXTURE_GOLD;
    private static ResourceLocation TEXTURE_DIAMOND;

    private final ModelTotem model;

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public RenderTotem() {
        TEXTURE_WOODEN = ResourceManager.getResourceWithExt(ResourceManager.CATEGORY_BLOCKS, "woodenTotem");
        TEXTURE_IRON = ResourceManager.getResourceWithExt(ResourceManager.CATEGORY_BLOCKS, "ironTotem");
        TEXTURE_GOLD = ResourceManager.getResourceWithExt(ResourceManager.CATEGORY_BLOCKS, "goldTotem");
        TEXTURE_DIAMOND = ResourceManager.getResourceWithExt(ResourceManager.CATEGORY_BLOCKS, "diamondTotem");

        model = new ModelTotem();
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
        if (entity instanceof TileEntityWoodenTotem) {
            bindTexture(TEXTURE_WOODEN);
        } else if (entity instanceof TileEntityIronTotem) {
            bindTexture(TEXTURE_IRON);
        } else if (entity instanceof TileEntityGoldTotem) {
            bindTexture(TEXTURE_GOLD);
        } else {
            bindTexture(TEXTURE_DIAMOND);
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(90 * entity.getBlockMetadata(), 0.0F, 1.0F, 0.0F);
        model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}
