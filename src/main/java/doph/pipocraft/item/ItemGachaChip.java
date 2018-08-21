package doph.pipocraft.item;

import doph.pipocraft.init.PCCreativeTabs;
import doph.pipocraft.system.Utils;
import net.minecraft.item.Item;

public class ItemGachaChip extends Item {
    public ItemGachaChip() {
        super();
        this.setCreativeTab(PCCreativeTabs.TAB_PIPOCRAFT);
        this.setFull3D();
        Utils.getInstance().setName(this, "gachachip");
    }
}
