package doph.pipocraft.init.register;

import doph.pipocraft.entity.EntityPipoMonkey;
import doph.pipocraft.system.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class EntityRegister {
    private int ID = 0;

    public void register(RegistryEvent.Register<EntityEntry> e) {
        e.getRegistry()
                .registerAll(EntityEntryBuilder.create()//
                        .entity(EntityPipoMonkey.class)
                        .id(new ResourceLocation(Ref.MODID, "pipo_monkey"), ID++)//
                        .name("pipo_monkey").egg(0xD0D000, 0x801515)//
                        .tracker(64, 20, false).build()//
        );
    }
}
