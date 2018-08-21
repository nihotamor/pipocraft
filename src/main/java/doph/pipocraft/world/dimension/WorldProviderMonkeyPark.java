/*package doph.pipocraft.world.dimension;

import doph.pipocraft.init.PCBiomes;
import doph.pipocraft.init.PCDimensions;
import doph.pipocraft.world.chunkgen.ChunkGeneratorMonkeyPark;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderMonkeyPark extends WorldProvider {
    public boolean hasSkyLight = true;

    public WorldProviderMonkeyPark() {
        super();
        biomeProvider = new BiomeProviderSingle(PCBiomes.hotSprings);
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorMonkeyPark(this.world);
    }

    @Override
    public DimensionType getDimensionType() {
        return PCDimensions.DIMENSION_MONKEY_PARK;
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return true;
    }

    @Override
    public boolean canSnowAt(BlockPos pos, boolean checkLight) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight() {
        return 1000.0F;
    }

    @Override
    public BiomeProvider getBiomeProvider() {
        return null;
    }

    @Override
    public boolean doesWaterVaporize() {
        return false;
    }

    @Override
    public boolean hasSkyLight() {
        return true;
    }

    @Override
    public boolean isNether() {
        return false;
    }

}
//*/