package doph.pipocraft.listener;

import java.util.Random;

import com.google.common.base.Predicate;

import doph.pipocraft.init.PCBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class PCOreGen implements IWorldGenerator {
    private static final int DIM_NETHER = -1;
    private static final int DIM_OVERWORLD = 0;
    private static final int DIM_END = 1;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
            IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
        case DIM_NETHER:
            break;
        case DIM_OVERWORLD:
            runGenerator(PCBlocks.gachaOre.getDefaultState(), 10, 10, 18, 86, BlockMatcher.forBlock(Blocks.STONE),
                    world, random, chunkX, chunkZ);
            runGenerator(PCBlocks.silverGachaOre.getDefaultState(), 6, 3, 0, 36, BlockMatcher.forBlock(Blocks.STONE),
                    world, random, chunkX, chunkZ);
            runGenerator(PCBlocks.goldGachaOre.getDefaultState(), 8, 1, 5, 13, BlockMatcher.forBlock(Blocks.STONE),
                    world, random, chunkX, chunkZ);
            break;
        case DIM_END:
            break;
        default:
            break;
        }
    }

    private void runGenerator(IBlockState block, int amount, int chance, int minHeight, int maxHeight,
            Predicate<IBlockState> replace, World world, Random rand, int chunkX, int chunkZ) {
        if (minHeight < 0 || maxHeight > 255 || minHeight > maxHeight) {
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        }
        WorldGenMinable generator = new WorldGenMinable(block, amount, replace);
        int heightdiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chance; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightdiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
