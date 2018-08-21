package doph.pipocraft.item.itemblock;

import doph.pipocraft.init.PCBlocks;
import doph.pipocraft.system.Utils;
import net.minecraft.item.ItemBlock;

public class ItemBlockGachaOre extends ItemBlock {

    public ItemBlockGachaOre() {
        super(PCBlocks.gachaOre);
        Utils.getInstance().setName(this, "gacha_ore");
    }

}
