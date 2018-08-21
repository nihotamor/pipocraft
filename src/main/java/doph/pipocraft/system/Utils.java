package doph.pipocraft.system;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Utils {
    private static Utils instance;

    private Utils() {

    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public Item setName(Item item, String name) {
        return item.setRegistryName(Ref.MODID, name).setUnlocalizedName(Ref.MODID + "." + name);
    }

    public Block setName(Block block, String name) {
        return block.setRegistryName(Ref.MODID, name).setUnlocalizedName(Ref.MODID + "." + name);
    }
}
