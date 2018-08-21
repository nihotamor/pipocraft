package doph.pipocraft.proxy;

import doph.pipocraft.init.register.EntityRenderRegister;
import doph.pipocraft.init.register.RenderRegister;
import doph.pipocraft.system.Ref;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Ref.MODID)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        new EntityRenderRegister().register();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    @Override
    public void onItemRegistered(RegistryEvent.Register<Item> e) {
        super.onItemRegistered(e);
    }

    @Override
    public void onBlockRegistered(RegistryEvent.Register<Block> e) {
        super.onBlockRegistered(e);
    }

    @Override
    public void onBiomeRegistered(RegistryEvent.Register<Biome> e) {
        super.onBiomeRegistered(e);
    }

    @Override
    public void onGuiRegistered(InitGuiEvent e) {
        super.onGuiRegistered(e);
    }

    @SubscribeEvent
    public static void onRenderRegistered(ModelRegistryEvent e) {
        new RenderRegister().registerRender(e);
    }
}
