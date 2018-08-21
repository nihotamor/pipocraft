package doph.pipocraft.client.render;

import doph.pipocraft.system.Ref;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPipoMonkey extends RenderLiving<EntityLiving> {

    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID + ":textures/entity/pipo_monkey.png");

    public RenderPipoMonkey(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return texture;
    }

}
