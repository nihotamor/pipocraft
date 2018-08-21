/*package doph.pipocraft.world.biome;

import java.util.Random;

import doph.pipocraft.init.PCBlocks;
import doph.pipocraft.system.Ref;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeMonkeyHotSprings extends Biome {

    public BiomeMonkeyHotSprings() {
        super(new BiomeProperties(Ref.MODID + ".HotSprings")//
                .setBaseHeight(1.0F).setHeightVariation(0.2F) //
                .setSnowEnabled().setTemperature(-5F) //
        );
        this.setRegistryName(Ref.MODID, "hot_springs");
        this.topBlock = PCBlocks.goldGachaOre.getDefaultState();
        this.fillerBlock = PCBlocks.gachaOre.getDefaultState();
        setSpawnables();
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        BiomeDecorator biomeDecorator = new BiomeDecorator();

        biomeDecorator.waterlilyPerChunk = 0;
        biomeDecorator.treesPerChunk = 2;
        biomeDecorator.extraTreeChance = 0.1F;
        biomeDecorator.flowersPerChunk = 0;
        biomeDecorator.grassPerChunk = 2;
        biomeDecorator.deadBushPerChunk = 0;
        biomeDecorator.mushroomsPerChunk = 0;
        biomeDecorator.reedsPerChunk = 0;
        biomeDecorator.cactiPerChunk = 0;
        biomeDecorator.gravelPatchesPerChunk = 1;
        biomeDecorator.sandPatchesPerChunk = 3;
        biomeDecorator.clayPerChunk = 1;
        biomeDecorator.bigMushroomsPerChunk = 0;
        biomeDecorator.generateFalls = true;

        return getModdedBiomeDecorator(biomeDecorator);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return new WorldGenTrees(false);
    }

    private void setSpawnables() {
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 12, 4, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 10, 4, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
        spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 4, 4));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 95, 4, 4));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 5, 1, 1));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
        spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitch.class, 5, 1, 1));
        spawnableWaterCreatureList.add(new Biome.SpawnListEntry(EntitySquid.class, 10, 4, 4));
        spawnableCaveCreatureList.add(new Biome.SpawnListEntry(EntityBat.class, 10, 8, 8));
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int seaLevel = worldIn.getSeaLevel();
        IBlockState surfaceBlock = topBlock;
        IBlockState mainBlock = fillerBlock;
        int j = -1;
        int noise = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int chunkX = x & 15;
        int chunkZ = z & 15;
        MutableBlockPos pos = new MutableBlockPos();

        for (int primerY = 255; primerY >= 0; --primerY) {
            // lay down bedrock layer
            if (primerY <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, BEDROCK);
            } else {
                IBlockState blockAtPosition = chunkPrimerIn.getBlockState(chunkX, primerY, chunkZ);

                if (blockAtPosition.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (blockAtPosition.getBlock() == fillerBlock) {
                    if (j == -1) {
                        // create area for ocean
                        if (noise <= 0) {
                            surfaceBlock = AIR;
                            mainBlock = fillerBlock;
                        }
                        // handle near sea level
                        else if (primerY >= seaLevel - 4 && primerY <= seaLevel + 1) {
                            surfaceBlock = topBlock;
                            mainBlock = fillerBlock;
                        }

                        // area exposed to air will be ocean
                        if (primerY < seaLevel
                                && (surfaceBlock == null || surfaceBlock.getMaterial() == Material.AIR)) {
                            if (getTemperature(pos.setPos(x, primerY, z)) < 0.15F) {
                                surfaceBlock = ICE;
                            } else {
                                surfaceBlock = WATER;
                            }
                        }

                        j = noise;

                        if (primerY >= seaLevel - 1) {
                            chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, surfaceBlock);
                        }
                        // fill in ocean bottom
                        else if (primerY < seaLevel - 7 - noise) {
                            surfaceBlock = AIR;
                            mainBlock = fillerBlock;
                            chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, fillerBlock);
                        } else {
                            chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, mainBlock);
                        }
                    } else if (j > 0) // fill in terrain with main block
                    {
                        --j;
                        chunkPrimerIn.setBlockState(chunkX, primerY, chunkZ, mainBlock);
                    }
                }
            }
        }
    }
}
//*/