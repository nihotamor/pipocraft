package doph.pipocraft.system;

import doph.pipocraft.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod(modid = Ref.MODID, name = Ref.MODNAME, version = Ref.MODVERSION)
public class PipoCraft {
    @SidedProxy(clientSide = Ref.CLIENTPROXY, serverSide = Ref.COMMONPROXY)
    private static CommonProxy proxy;
    @Instance(Ref.MODID)
    public static PipoCraft INSTANCE;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @SubscribeEvent
    public void onItemRegistered(RegistryEvent.Register<Item> e) {
        proxy.onItemRegistered(e);
    }

    @SubscribeEvent
    public void onBlockRegistered(RegistryEvent.Register<Block> e) {
        proxy.onBlockRegistered(e);
    }

    @SubscribeEvent
    public void onEntityRegistered(RegistryEvent.Register<EntityEntry> e) {
        proxy.onEntityRegistered(e);
    }

    @SubscribeEvent
    public void onBiomeRegistered(RegistryEvent.Register<Biome> e) {
        proxy.onBiomeRegistered(e);
    }

    @SubscribeEvent
    public void onGUIRegistered(InitGuiEvent e) {
        proxy.onGuiRegistered(e);
    }
}
