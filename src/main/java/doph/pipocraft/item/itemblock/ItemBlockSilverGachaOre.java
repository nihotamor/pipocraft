package doph.pipocraft.item.itemblock;

import doph.pipocraft.init.PCBlocks;
import doph.pipocraft.system.Utils;
import net.minecraft.item.ItemBlock;

public class ItemBlockSilverGachaOre extends ItemBlock {

    public ItemBlockSilverGachaOre() {
        super(PCBlocks.silverGachaOre);
        Utils.getInstance().setName(this, "silver_gacha_ore");
    }

}
