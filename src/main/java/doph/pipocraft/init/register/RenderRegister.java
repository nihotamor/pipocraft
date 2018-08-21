package doph.pipocraft.init.register;

import doph.pipocraft.init.PCItems;
import doph.pipocraft.system.Ref;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;

public class RenderRegister {
    public void registerRender(ModelRegistryEvent e) {
        OBJLoader.INSTANCE.addDomain(Ref.MODID);

        register(PCItems.gachaOre);
        register(PCItems.silverGachaOre);
        register(PCItems.goldGachaOre);
        register(PCItems.transferMachine);
        register(PCItems.gachachip);
        register(PCItems.silverGachachip);
        register(PCItems.goldGachachip);
        register(PCItems.stunClub);
        register(PCItems.timeNet);
    }

    public void register(Item item, String... subName) {
        int len = subName.length;

        if (len == 0) {
            ModelLoader.setCustomModelResourceLocation(item, 0, //
                    new ModelResourceLocation(Ref.MODID + ":" + //
                            item.getUnlocalizedName().substring(15), "inventory"));
        } else {
            for (int i = 0; i < len; i++) {
                ModelLoader.setCustomModelResourceLocation(item, i, //
                        new ModelResourceLocation(Ref.MODID + ":" + //
                                item.getUnlocalizedName().substring(15) //
                                + "_" + subName[i], "inventory"));
            }
        }
    }
}
