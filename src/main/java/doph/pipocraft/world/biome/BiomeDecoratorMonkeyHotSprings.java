/*package doph.pipocraft.world.biome;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeDecorator;

public class BiomeDecoratorMonkeyHotSprings extends BiomeDecorator{

    Predicate<IBlockState> replaceablePredicate = new PredicateMonkeyPark();

    private int dirtSize = 33;
    private int gravelSize = 33;
    private int graniteSize = 33;
    private int dioriteSize = 33;
    private int andesiteSize = 33;
    private int coalSize = 17;
    private int ironSize = 9;
    private int goldSize = 9;
    private int redstoneSize = 9;
    private int diamondSize = 8;
    private int lapisSize = 8;

    private int dirtCount = 10;
    private int gravelCount = 8;
    private int dioriteCount = 10;
    private int graniteCount = 10;
    private int andesiteCount = 10;
    private int coalCount = 20;
    private int ironCount = 20;
    private int goldCount = 2;
    private int redstoneCount = 8;
    private int diamondCount = 1;
    private int lapisCount = 1;

    private int lapisCenterHeight =6;
    private int lapisSpread = 16;

    private int oreGenMinHeight = 0;

    private int dirtMaxHeight = 255;
    private int gravelMaxHeight = 255;
    private int dioriteMaxHeight = 80;
    private int graniteMaxHeight = 80;
    private int andesiteMaxHeight = 80;
    private int coalMaxHeight = 126;
    private int ironMaxHeight = 64;
    private int goldMaxHeight = 32;
    private int redstoneMaxHeight = 16;
    private int diamondMaxHeight = 16;

    static class PredicateMonkeyPark implements Predicate<IBlockState> {
        public List<Block> blockList = new ArrayList<>();
        private PredicateMonkeyPark() { }
        @Override
        public boolean apply(IBlockState parBlockState) {
            if(parBlockState != null && blockList.contains(parBlockState.getBlock())) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
//*/