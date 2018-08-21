package doph.pipocraft.item.itemblock;

import doph.pipocraft.init.PCBlocks;
import doph.pipocraft.system.Utils;
import net.minecraft.item.ItemBlock;

public class ItemBlockGoldGachaOre extends ItemBlock {

    public ItemBlockGoldGachaOre() {
        super(PCBlocks.goldGachaOre);
        Utils.getInstance().setName(this, "gold_gacha_ore");
    }

}
