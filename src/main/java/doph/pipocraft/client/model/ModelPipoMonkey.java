package doph.pipocraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPipoMonkey extends ModelBase {
    ModelRenderer head;
    ModelRenderer helmet;
    ModelRenderer lamp;
    ModelRenderer chest;
    ModelRenderer arml;
    ModelRenderer legl;
    ModelRenderer footl;
    ModelRenderer armr;
    ModelRenderer legr;
    ModelRenderer footr;

    public ModelPipoMonkey() {
        super();
        textureWidth = 64;
        textureHeight = 32;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, 0F, -4F, 8, 8, 8);
        helmet = new ModelRenderer(this, 24, 0);
        helmet.addBox(-2F, -3F, -2F, 4, 4, 4);
        lamp = new ModelRenderer(this, 32, 0);
        lamp.addBox(-4F, -0.44F, -4F, 8, 3, 8);
        chest = new ModelRenderer(this, 0, 16);
        chest.addBox(-3F, 8F, -2F, 6, 8, 4);
        arml = new ModelRenderer(this, 20, 16);
        arml.addBox(-5F, 8F, -1F, 2, 8, 2);
        legl = new ModelRenderer(this, 28, 16);
        legl.addBox(-2.5F, 16F, -1F, 2, 6, 2);
        footl = new ModelRenderer(this, 34, 26);
        footl.addBox(-3F, 22F, -3F, 3, 2, 4);
        armr = new ModelRenderer(this, 20, 16);
        armr.addBox(3F, 8F, -1F, 2, 8, 2);
        legr = new ModelRenderer(this, 28, 16);
        legr.addBox(0.5F, 16F, -1F, 2, 6, 2);
        footr = new ModelRenderer(this, 34, 26);
        footr.addBox(0F, 22F, -3F, 3, 2, 4);
    }

    @Override
    public void render(Entity entity, float F, float f1, float f2, float f3, float f4, float f5) {
        head.render(f5);
        helmet.render(f5);
        lamp.render(f5 * 1.125F);
        chest.render(f5);
        arml.render(f5);
        legl.render(f5);
        footl.render(f5);
        armr.render(f5);
        legr.render(f5);
        footr.render(f5);
    }
}
