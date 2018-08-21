package doph.pipocraft.init.register;

import doph.pipocraft.tileentity.TileEntityTransferMachine;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegister {
    public void register() {
        GameRegistry.registerTileEntity(TileEntityTransferMachine.class, "transfer_machine");
    }
}
