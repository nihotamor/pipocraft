package doph.pipocraft.init.register;

import doph.pipocraft.item.ItemGachaChip;
import doph.pipocraft.item.ItemGoldGachaChip;
import doph.pipocraft.item.ItemSilverGachaChip;
import doph.pipocraft.item.ItemStunClub;
import doph.pipocraft.item.ItemTimeNet;
import doph.pipocraft.item.itemblock.ItemBlockGachaOre;
import doph.pipocraft.item.itemblock.ItemBlockGoldGachaOre;
import doph.pipocraft.item.itemblock.ItemBlockSilverGachaOre;
import doph.pipocraft.item.itemblock.ItemBlockTransferMachine;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ItemRegister {
    public void register(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll( //
                new ItemBlockGachaOre(), //
                new ItemBlockSilverGachaOre(), //
                new ItemBlockGoldGachaOre(), //
                new ItemBlockTransferMachine(), //

                new ItemGachaChip(), //
                new ItemSilverGachaChip(), //
                new ItemGoldGachaChip(), //
                new ItemStunClub(), //
                new ItemTimeNet()//
        );
    }
}
