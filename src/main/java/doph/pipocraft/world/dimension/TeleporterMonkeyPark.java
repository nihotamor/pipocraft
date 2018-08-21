/*package doph.pipocraft.world.dimension;

import java.util.Random;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterMonkeyPark extends Teleporter {

    public int dim;
    //private final WorldServer worldServerInstance;
    //private final Random random;
    private double prevX, prevY, prevZ;
    //private final Long2ObjectMap<TeleporterMonkeyPark.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);

    public TeleporterMonkeyPark(WorldServer worldIn, int dim2, double x, double y, double z) {
        super(worldIn);
        //this.worldServerInstance = worldIn;
       // this.random = new Random(worldIn.getSeed());
        this.dim = dim2;
        prevX = x;
        prevY = y;
        prevZ = z;
    }

    @Override
    public void placeInPortal(Entity entity, float rotationYaw) {
        if (dim < 2) {
            this.makePortal(entity);
        } else if (dim > 1) {
            entity.setLocationAndAngles(prevX, prevY, prevZ, entity.rotationYaw, 0.0F);
            entity.motionX = 0.5;
            entity.motionY = 0.5;
            entity.motionZ = 0.5;
        }
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
        return false;
    }

    public class PortalPosition extends BlockPos{
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdate) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.lastUpdateTime = lastUpdate;
        }
    }

}
//*/