/*package doph.pipocraft.init;

import doph.pipocraft.world.WorldGenMonkeyPark;
import doph.pipocraft.world.dimension.WorldProviderMonkeyPark;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PCDimensions {
    public static DimensionType DIMENSION_MONKEY_PARK;

    public void register() {
        DIMENSION_MONKEY_PARK = DimensionType.register("Monkey Park", //
                "_monkey_park", DimensionManager.getNextFreeDimId(), //
                WorldProviderMonkeyPark.class, false);
        DimensionManager.registerDimension(DIMENSION_MONKEY_PARK.getId(), DIMENSION_MONKEY_PARK);
        GameRegistry.registerWorldGenerator(new WorldGenMonkeyPark(), 10);
    }
}
//*/