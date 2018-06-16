package ru.kordum.totemDefender.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import ru.kordum.totemDefender.block.BlockTotem;
import ru.kordum.totemDefender.entity.TileEntityTotem;
import ru.kordum.totemDefender.model.ModelTotem;
import ru.kordum.totemDefender.util.ModResources;

public class RenderTotem extends TileEntitySpecialRenderer<TileEntityTotem> {
    private static ResourceLocation TEXTURE_WOODEN;
    private static ResourceLocation TEXTURE_IRON;
    private static ResourceLocation TEXTURE_GOLDEN;
    private static ResourceLocation TEXTURE_DIAMOND;

    private final ModelTotem model;

    public RenderTotem() {
        model = new ModelTotem();
        TEXTURE_WOODEN = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "wooden_totem.png");
        TEXTURE_IRON = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "iron_totem.png");
        TEXTURE_GOLDEN = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "golden_totem.png");
        TEXTURE_DIAMOND = ModResources.getResource(ModResources.CATEGORY_BLOCKS, "diamond_totem.png");
    }

    @Override
    public void render(TileEntityTotem entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        BlockTotem.EnumType type = entity.getType();
        if (type == BlockTotem.EnumType.WOODEN) {
            bindTexture(TEXTURE_WOODEN);
        } else if (type == BlockTotem.EnumType.IRON) {
            bindTexture(TEXTURE_IRON);
        } else if (type == BlockTotem.EnumType.GOLDEN) {
            bindTexture(TEXTURE_GOLDEN);
        } else {
            bindTexture(TEXTURE_DIAMOND);
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.rotate(90 * entity.getBlockMetadata(), 0.0F, 1.0F, 0.0F);
        model.render(null, 0.0F, 0.0F, 0.0F, 0, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}
