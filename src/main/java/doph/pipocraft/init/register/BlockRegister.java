package doph.pipocraft.init.register;

import doph.pipocraft.block.BlockGachaOre;
import doph.pipocraft.block.BlockGoldGachaOre;
import doph.pipocraft.block.BlockSilverGachaOre;
import doph.pipocraft.block.BlockTransferMachine;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public class BlockRegister {
    public void register(RegistryEvent.Register<Block> e) {
        e.getRegistry().registerAll( //
                new BlockGachaOre(), //
                new BlockSilverGachaOre(), //
                new BlockGoldGachaOre(), //
                new BlockTransferMachine()//
        );
    }
}
