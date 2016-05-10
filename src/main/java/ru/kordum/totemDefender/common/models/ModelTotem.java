package ru.kordum.totemDefender.common.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTotem extends ModelBase {
    private ModelRenderer shape1;
    private ModelRenderer shape2;
    private ModelRenderer shape3;
    private ModelRenderer shape4;
    private ModelRenderer shape5;
    private ModelRenderer shape6;
    private ModelRenderer shape7;
    private ModelRenderer shape8;
    private ModelRenderer shape9;
    private ModelRenderer shape10;
    private ModelRenderer shape11;
    private ModelRenderer shape12;
    private ModelRenderer shape13;
    private ModelRenderer shape14;

    public ModelTotem() {
        textureWidth = 64;
        textureHeight = 64;

        shape1 = new ModelRenderer(this, 0, 34);
        shape1.addBox(0F, 0F, 0F, 14, 1, 14, 0F);
        shape1.setRotationPoint(-7F, 23F, -7F);
        shape1.setTextureOffset(0, 34);

        shape2 = new ModelRenderer(this, 0, 17);
        shape2.addBox(0F, 0F, 0F, 8, 9, 8, 0F);
        shape2.setRotationPoint(-4F, 14F, -4F);
        shape2.setTextureOffset(0, 17);
        shape2.mirror = true;

        shape3 = new ModelRenderer(this, 32, 17);
        shape3.addBox(0F, 0F, 0F, 6, 2, 6, 0F);
        shape3.setRotationPoint(-3F, 12F, -3F);
        shape3.setTextureOffset(32, 17);

        shape4 = new ModelRenderer(this, 0, 0);
        shape4.addBox(0F, 0F, 0F, 8, 9, 8, 0F);
        shape4.setRotationPoint(-4F, 3F, -4F);

        shape5 = new ModelRenderer(this, 32, 25);
        shape5.addBox(0F, 0F, 0F, 6, 2, 6, 0F);
        shape5.setRotationPoint(-3F, 1F, -3F);
        shape5.setTextureOffset(32, 25);

        shape6 = new ModelRenderer(this, 32, 0);
        shape6.addBox(0F, 0F, 0F, 8, 9, 8, 0F);
        shape6.setRotationPoint(-4F, -8F, -4F);
        shape6.setTextureOffset(32, 0);

        shape7 = new ModelRenderer(this, 0, 49);
        shape7.addBox(0F, 0F, 0F, 2, 7, 1, 0F);
        shape7.setRotationPoint(-6F, 4F, 0F);
        shape7.setTextureOffset(0, 49);

        shape8 = new ModelRenderer(this, 56, 41);
        shape8.addBox(0F, 3F, 0F, 2, 6, 1, 0F);
        shape8.setRotationPoint(-8F, 0F, 0F);
        shape8.setTextureOffset(56, 41);

        shape9 = new ModelRenderer(this, 56, 22);
        shape9.addBox(0F, 0F, 0F, 2, 5, 1, 0F);
        shape9.setRotationPoint(-10F, 2F, 0F);
        shape9.setTextureOffset(56, 22);

        shape10 = new ModelRenderer(this, 56, 17);
        shape10.addBox(0F, 0F, 0F, 1, 4, 1, 0F);
        shape10.setRotationPoint(-11F, 1F, 0F);
        shape10.setTextureOffset(56, 17);

        shape11 = new ModelRenderer(this, 60, 17);
        shape11.addBox(0F, 0F, 0F, 1, 4, 1, 0F);
        shape11.setRotationPoint(10F, 1F, 0F);
        shape11.setTextureOffset(60, 17);

        shape12 = new ModelRenderer(this, 56, 28);
        shape12.addBox(0F, 0F, 0F, 2, 5, 1, 0F);
        shape12.setRotationPoint(8F, 2F, 0F);
        shape12.setTextureOffset(56, 28);

        shape13 = new ModelRenderer(this, 56, 34);
        shape13.addBox(0F, 0F, 0F, 2, 6, 1, 0F);
        shape13.setRotationPoint(6F, 3F, 0F);
        shape13.setTextureOffset(56, 34);

        shape14 = new ModelRenderer(this, 6, 49);
        shape14.addBox(0F, 0F, 0F, 2, 7, 1, 0F);
        shape14.setRotationPoint(4F, 4F, 0F);
        shape14.setTextureOffset(6, 49);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        shape2.render(f5);
        shape1.render(f5);
        shape3.render(f5);
        shape4.render(f5);
        shape5.render(f5);
        shape6.render(f5);
        shape7.render(f5);
        shape8.render(f5);
        shape9.render(f5);
        shape10.render(f5);
        shape11.render(f5);
        shape12.render(f5);
        shape13.render(f5);
        shape14.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
