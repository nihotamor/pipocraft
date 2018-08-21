/*package doph.pipocraft.world.chunkgen;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import doph.pipocraft.init.PCBiomes;
import doph.pipocraft.init.PCBlocks;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkGeneratorMonkeyPark implements IChunkGenerator {
    protected World world;
    protected ChunkPrimer chunkPrimer = new ChunkPrimer();
    protected int chunkX, chunkZ;
    protected ChunkPos chunkPos;
    protected boolean mapFeaturesEnabled = true;
    protected Random rand;

    protected WorldType terrainType;
    protected Biome biome = PCBiomes.hotSprings;
    protected IBlockState MAIN_BLOCK = PCBlocks.gachaOre.getDefaultState();
    protected IBlockState TOP_BLOCK = PCBlocks.silverGachaOre.getDefaultState();
    protected IBlockState WATER = Blocks.WATER.getDefaultState();

    protected NoiseGeneratorOctaves minLimitPerlinNoise;
    protected NoiseGeneratorOctaves maxLimitPerlinNoise;
    protected NoiseGeneratorOctaves mainPerlinNoise;
    protected NoiseGeneratorPerlin surfaceNoise;
    protected NoiseGeneratorOctaves scaleNoise;
    protected NoiseGeneratorOctaves depthNoise;
    protected NoiseGeneratorOctaves forestNoise;

    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;

    protected double[] heightMap = new double[825];
    protected float[] biomeWeights = new float[25];
    protected double[] depthBuffer = new double[256];

    protected MapGenBase caveGenerator = new MapGenCaves();
    protected MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();

    private boolean useTemples = false;
    private boolean useCaves = true;
    private double depthNoiseScaleX = 200.0D;
    private double depthNoiseScaleZ = 200.0D;
    private double depthNoiseScaleExponent = 0.5D;
    private int coordScale = 684;
    private int mainNoiseScaleX = 80;
    private int mainNoiseScaleY = 160;
    private int mainNoiseScaleZ = 80;
    private int heightScale = 684;
    private int biomeDepthOffSet = 0;
    private int biomeScaleOffset = 0;
    private double heightStretch = 12;
    private double baseSize = 8.5D;
    private double lowerLimitScale = 512D;
    private double upperLimitScale = 512D;
    private float biomeDepthWeight = 1.0F;
    private float biomeScaleWeight = 1.0F;

    public ChunkGeneratorMonkeyPark(World worldIn) {
        // DEBUG
        System.out.println("Constructing ChunkGeneratorCloud");

        world = worldIn;
        rand = new Random(world.getSeed());
        terrainType = world.getWorldInfo().getTerrainType();
        mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();
        world.setSeaLevel(63);

        initNoiseGenerators();
        postTerrainGenEvents();
        setBiomeWeights();
        postNoiseEvent();
    }

    private void postNoiseEvent() {
        InitNoiseGensEvent.ContextOverworld ctx = new InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise,
                maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise, forestNoise);
        ctx = TerrainGen.getModdedNoiseGenerators(world, rand, ctx);
        minLimitPerlinNoise = ctx.getLPerlin1();
        maxLimitPerlinNoise = ctx.getLPerlin2();
        mainPerlinNoise = ctx.getPerlin();
        surfaceNoise = ctx.getHeight();
        scaleNoise = ctx.getScale();
        depthNoise = ctx.getDepth();
        forestNoise = ctx.getForest();
    }

    private void setBiomeWeights() {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt(i * i + j * j + 0.2F);
                biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }
    }

    protected void initNoiseGenerators() {
        minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
        surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
        scaleNoise = new NoiseGeneratorOctaves(rand, 10);
        depthNoise = new NoiseGeneratorOctaves(rand, 16);
        forestNoise = new NoiseGeneratorOctaves(rand, 8);
    }

    protected void postTerrainGenEvents() {
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, InitMapGenEvent.EventType.CAVE);
        scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator,
                InitMapGenEvent.EventType.SCATTERED_FEATURE);
    }

    @Override
    public Chunk generateChunk(int parChunkX, int parChunkZ) {
        chunkX = parChunkX;
        chunkZ = parChunkZ;
        rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        generateHeightmap();
        setBlocksInChunk();
        replaceBiomeBlocks();

        if (useCaves) {
            caveGenerator.generate(world, chunkX, chunkZ, chunkPrimer);
        }

        if (mapFeaturesEnabled) {
            if (useTemples) {
                scatteredFeatureGenerator.generate(world, chunkX, chunkZ, chunkPrimer);
            }
        }

        Chunk chunk = new Chunk(world, chunkPrimer, parChunkX, parChunkZ);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte) Biome.getIdForBiome(biome);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    public void setBlocksInChunk() {
        for (int i = 0; i < 4; ++i) {
            int j = i * 5;
            for (int l = 0; l < 4; ++l) {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                for (int i2 = 0; i2 < 32; ++i2) {
                    for (int j2 = 0; j2 < 8; ++j2) {
                        double d10 = heightMap[i1 + i2];
                        double d11 = heightMap[j1 + i2];

                        for (int k2 = 0; k2 < 4; ++k2) {
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2) {
                                if ((lvt_45_1_ += d16) > 0.0D) {
                                    chunkPrimer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, MAIN_BLOCK);
                                } else if (i2 * 8 + j2 < world.getSeaLevel()) {
                                    chunkPrimer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, WATER);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void replaceBiomeBlocks() {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, chunkX, chunkZ, chunkPrimer, world)) {
            return;
        }
        depthBuffer = surfaceNoise.getRegion(depthBuffer, chunkX * 16, chunkZ * 16, 16, 16, 0.0625D, 0.0625D, 1.0D);

        for (int xInChunk = 0; xInChunk < 16; ++xInChunk) {
            for (int zInChunk = 0; zInChunk < 16; ++zInChunk) {
                biome.genTerrainBlocks(world, rand, chunkPrimer, chunkX * 16 + xInChunk, chunkZ * 16 + zInChunk,
                        depthBuffer[zInChunk + xInChunk * 16]);
            }
        }
    }

    protected void generateHeightmap() {
        int xOffset = chunkX * 4;
        int zOffset = chunkZ * 4;
        depthRegion = depthNoise.generateNoiseOctaves(depthRegion, xOffset, zOffset, 5, 5, depthNoiseScaleX,
                depthNoiseScaleZ, depthNoiseScaleExponent);
        mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, xOffset, 0, zOffset, 5, 33, 5,
                coordScale / mainNoiseScaleX, heightScale / mainNoiseScaleY, coordScale / mainNoiseScaleZ);
        minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, xOffset, 0, zOffset, 5, 33, 5,
                coordScale, heightScale, coordScale);
        maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, xOffset, 0, zOffset, 5, 33, 5,
                coordScale, heightScale, coordScale);

        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 5; ++l) {
                float accumulatedHeightVariation = 0.0F;
                float accumulatedHeight = 0.0F;
                float accumulatedWeightedHeightFactor = 0.0F;

                for (int j1 = -2; j1 <= 2; ++j1) {
                    for (int k1 = -2; k1 <= 2; ++k1) {
                        float baseHeight = biomeDepthOffSet + biome.getBaseHeight() * biomeDepthWeight;
                        float heightVariation = biomeScaleOffset + biome.getHeightVariation() * biomeScaleWeight;

                        float weightedHeightFactor = biomeWeights[j1 + 2 + (k1 + 2) * 5] / (baseHeight + 2.0F);

                        accumulatedHeightVariation += heightVariation * weightedHeightFactor;
                        accumulatedHeight += baseHeight * weightedHeightFactor;
                        accumulatedWeightedHeightFactor += weightedHeightFactor;
                    }
                }

                accumulatedHeightVariation = accumulatedHeightVariation / accumulatedWeightedHeightFactor * 0.9F + 0.1F;
                accumulatedHeight = (accumulatedHeight / accumulatedWeightedHeightFactor * 4.0F - 1.0F) / 8.0F;
                double depthBy8k = depthRegion[j] / 8000.0D;

                if (depthBy8k < 0.0D) {
                    depthBy8k = -depthBy8k * 0.3D;
                }

                depthBy8k = depthBy8k * 3.0D - 2.0D;

                if (depthBy8k < 0.0D) {
                    depthBy8k = depthBy8k / 2.0D;

                    if (depthBy8k < -1.0D) {
                        depthBy8k = -1.0D;
                    }

                    depthBy8k = depthBy8k / 2.8D;
                } else {
                    if (depthBy8k > 1.0D) {
                        depthBy8k = 1.0D;
                    }

                    depthBy8k = depthBy8k / 8.0D;
                }

                ++j;

                for (int l1 = 0; l1 < 33; ++l1) {
                    double d1 = (l1 - (baseSize + ((accumulatedHeight + depthBy8k * 0.2D) * baseSize / 8.0D) * 4.0D))
                            * heightStretch / 2.0D / accumulatedHeightVariation;

                    if (d1 < 0.0D) {
                        d1 *= 4.0D;
                    }

                    double minLimitScaled = minLimitRegion[i] / lowerLimitScale;
                    double maxLimitScaled = maxLimitRegion[i] / upperLimitScale;
                    double noiseValue = (mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double linearInterpHeight = MathHelper.clampedLerp(minLimitScaled, maxLimitScaled, noiseValue) - d1;

                    if (l1 > 29) {
                        linearInterpHeight = linearInterpHeight * (1.0D - ((l1 - 29) / 3.0F))
                                + -10.0D * ((l1 - 29) / 3.0F);
                    }

                    heightMap[i] = linearInterpHeight;
                    ++i;
                }
            }
        }
    }

    @Override
    public void populate(int parChunkX, int parChunkZ) {
        BlockFalling.fallInstantly = true;

        int chunkStartXInWorld = parChunkX * 16;
        int chunkStartZInWorld = parChunkZ * 16;
        rand.setSeed(world.getSeed());
        long k = rand.nextLong() / 2L * 2L + 1L;
        long l = rand.nextLong() / 2L * 2L + 1L;
        rand.setSeed(parChunkX * k + parChunkZ * l ^ world.getSeed());
        chunkPos = new ChunkPos(parChunkX, parChunkZ);
        boolean villageHasGenerated = false;

        ForgeEventFactory.onChunkPopulate(true, this, world, rand, parChunkX, parChunkZ, villageHasGenerated);

        if(mapFeaturesEnabled) {
            generateMapFeatures();
        }

        biome.decorate(world, rand, new BlockPos(chunkStartXInWorld, 0, chunkStartZInWorld));

        if (TerrainGen.populate(this, world, rand, parChunkX, parChunkZ, villageHasGenerated,
                PopulateChunkEvent.Populate.EventType.CUSTOM)) {
            WorldEntitySpawner.performWorldGenSpawning(world, biome, chunkStartXInWorld + 8, chunkStartZInWorld + 8, 16,
                    16, rand);
        }

        ForgeEventFactory.onChunkPopulate(false, this, world, rand, parChunkX, parChunkZ, villageHasGenerated);

        BlockFalling.fallInstantly = false;
    }

    private boolean generateMapFeatures() {
        return true;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return true;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = world.getBiome(pos);

        if (mapFeaturesEnabled) {
            if (creatureType == EnumCreatureType.MONSTER && scatteredFeatureGenerator.isSwampHut(pos)) {
                return scatteredFeatureGenerator.getMonsters();
            }
        }

        return biome.getSpawnableList(creatureType);
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
    {
        if (!mapFeaturesEnabled)
        {
            return false;
        }
        else
        {
            return "HotSprings".equals(structureName) && scatteredFeatureGenerator != null ? scatteredFeatureGenerator.isInsideStructure(pos) : false;
        }
    }

    @Override
    @Nullable
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    {
        if (!mapFeaturesEnabled)
        {
            return null;
        }
        else
        {
            return "HotSprings".equals(structureName) && scatteredFeatureGenerator != null
                    ? scatteredFeatureGenerator.getNearestStructurePos(worldIn, position, findUnexplored)
                    : null;
        }
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
        if (mapFeaturesEnabled)
        {
            if (useTemples)
            {
                scatteredFeatureGenerator.generate(world, x, z, (ChunkPrimer) null);
            }
        }
    }
}
//*/