package doph.pipocraft.init.register;

import doph.pipocraft.client.model.ModelPipoMonkey;
import doph.pipocraft.client.render.RenderPipoMonkey;
import doph.pipocraft.entity.EntityPipoMonkey;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRenderRegister {
    public void register() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPipoMonkey.class, new IRenderFactory<EntityPipoMonkey>() {

            @Override
            public Render<? super EntityPipoMonkey> createRenderFor(RenderManager manager) {
                return new RenderPipoMonkey(manager, new ModelPipoMonkey(), 0.3f);
            }

        });
    }
}
