package doph.pipocraft.item.itemblock;

import doph.pipocraft.init.PCBlocks;
import doph.pipocraft.system.Utils;
import net.minecraft.item.ItemBlock;

public class ItemBlockTransferMachine extends ItemBlock {

    public ItemBlockTransferMachine() {
        super(PCBlocks.transferMachine);
        Utils.getInstance().setName(this, "transfer_machine");
    }

}
