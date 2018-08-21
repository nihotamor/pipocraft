/*package doph.pipocraft.init.register;

import doph.pipocraft.init.PCBiomes;
import doph.pipocraft.world.biome.BiomeMonkeyHotSprings;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;

public class BiomeRegister {
    public void register(RegistryEvent.Register<Biome> e) {
        e.getRegistry().registerAll(//
                new BiomeMonkeyHotSprings()//
        );

        BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(PCBiomes.hotSprings, 100));
    }
}
//*/