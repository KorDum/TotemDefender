package ru.kordum.totemDefender.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.kordum.totemDefender.common.entities.EntityProjectile;

@SideOnly(Side.CLIENT)
public class RenderProjectile extends Render {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public RenderProjectile() {
        super(Minecraft.getMinecraft().getRenderManager());
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    protected ResourceLocation func_180556_a(EntityProjectile entity) {
        return TextureMap.locationBlocksTexture;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.func_180556_a((EntityProjectile) entity);
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    public void doRender(EntityProjectile entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        TextureAtlasSprite sprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.fire_charge);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        float f3 = sprite.getMinU();
        float f4 = sprite.getMaxU();
        float f5 = sprite.getMinV();
        float f6 = sprite.getMaxV();
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.setNormal(0.0F, 1.0F, 0.0F);
        worldrenderer.addVertexWithUV((double) (0.0F - f8), (double) (0.0F - f9), 0.0D, (double) f3, (double) f6);
        worldrenderer.addVertexWithUV((double) (f7 - f8), (double) (0.0F - f9), 0.0D, (double) f4, (double) f6);
        worldrenderer.addVertexWithUV((double) (f7 - f8), (double) (1.0F - f9), 0.0D, (double) f4, (double) f5);
        worldrenderer.addVertexWithUV((double) (0.0F - f8), (double) (1.0F - f9), 0.0D, (double) f3, (double) f5);
        tessellator.draw();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        doRender((EntityProjectile) entity, x, y, z, p_76986_8_, partialTicks);
    }
}
