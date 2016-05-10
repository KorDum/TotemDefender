package ru.kordum.totemDefender.client.renders;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderProjectile extends Render {

    //---------------------------------------------------------------------------
    //
    // CONSTRUCTOR
    //
    //---------------------------------------------------------------------------

    public RenderProjectile() {
        super();
    }

    //---------------------------------------------------------------------------
    //
    // PRIVATE METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    //---------------------------------------------------------------------------
    //
    // PUBLIC METHODS
    //
    //---------------------------------------------------------------------------

    @Override
    public void doRender(Entity entity, double x, double y, double z, float p_doRender_8_, float p_doRender_9_) {
        GL11.glPushMatrix();
        this.bindEntityTexture(entity);
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        IIcon var11 = Items.fire_charge.getIconFromDamage(0);
        Tessellator var12 = Tessellator.instance;
        float var13 = var11.getMinU();
        float var14 = var11.getMaxU();
        float var15 = var11.getMinV();
        float var16 = var11.getMaxV();
        float var17 = 1.0F;
        float var18 = 0.5F;
        float var19 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        var12.startDrawingQuads();
        var12.setNormal(0.0F, 1.0F, 0.0F);
        var12.addVertexWithUV((double) (0.0F - var18), (double) (0.0F - var19), 0.0D, (double) var13, (double) var16);
        var12.addVertexWithUV((double) (var17 - var18), (double) (0.0F - var19), 0.0D, (double) var14, (double) var16);
        var12.addVertexWithUV((double) (var17 - var18), (double) (1.0F - var19), 0.0D, (double) var14, (double) var15);
        var12.addVertexWithUV((double) (0.0F - var18), (double) (1.0F - var19), 0.0D, (double) var13, (double) var15);
        var12.draw();
        GL11.glPopMatrix();
    }
}
