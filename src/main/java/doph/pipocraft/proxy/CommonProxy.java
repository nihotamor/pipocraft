package doph.pipocraft.proxy;

import doph.pipocraft.gui.GuiHandler;
import doph.pipocraft.init.register.BlockRegister;
import doph.pipocraft.init.register.EntityRegister;
import doph.pipocraft.init.register.ItemRegister;
import doph.pipocraft.init.register.TileEntityRegister;
import doph.pipocraft.listener.PCOreGen;
import doph.pipocraft.system.PipoCraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        //new PCDimensions().register();
    }

    public void init(FMLInitializationEvent e) {
        GameRegistry.registerWorldGenerator(new PCOreGen(), 0);
        new TileEntityRegister().register();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

    public void onItemRegistered(RegistryEvent.Register<Item> e) {
        new ItemRegister().register(e);
    }

    public void onBlockRegistered(RegistryEvent.Register<Block> e) {
        new BlockRegister().register(e);
    }

    public void onEntityRegistered(RegistryEvent.Register<EntityEntry> e) {
        new EntityRegister().register(e);
    }

    public void onBiomeRegistered(RegistryEvent.Register<Biome> e) {
        // new BiomeRegister().register(e);
    }

    public void onGuiRegistered(InitGuiEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(PipoCraft.INSTANCE, new GuiHandler());
    }
}
