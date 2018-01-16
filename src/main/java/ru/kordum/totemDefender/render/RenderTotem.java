package ru.kordum.totemDefender.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import ru.kordum.totemDefender.entity.TileEntityGoldenTotem;
import ru.kordum.totemDefender.entity.TileEntityIronTotem;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.entity.TileEntityWoodenTotem;
import ru.kordum.totemDefender.model.ModelTotem;
import ru.kordum.totemDefender.util.ModResources;

public class RenderTotem extends TileEntitySpecialRenderer<TileEntityTotem> {
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
        model = new ModelTotem();
        TEXTURE_WOODEN = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "wooden_totem.png");
        TEXTURE_IRON = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "iron_totem.png");
        TEXTURE_GOLD = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "gold_totem.png");
        TEXTURE_DIAMOND = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "diamond_totem.png");
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public void renderTileEntityAt(TileEntityTotem entity, double posX, double posY, double posZ, float scale, int meta) {
        if (entity instanceof TileEntityWoodenTotem) {
            bindTexture(TEXTURE_WOODEN);
        } else if (entity instanceof TileEntityIronTotem) {
            bindTexture(TEXTURE_IRON);
        } else if (entity instanceof TileEntityGoldenTotem) {
            bindTexture(TEXTURE_GOLD);
        } else {
            bindTexture(TEXTURE_DIAMOND);
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX + 0.5F, (float) posY + 1.5F, (float) posZ + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.rotate(90 * entity.getBlockMetadata(), 0.0F, 1.0F, 0.0F);
        model.render(null, 0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}