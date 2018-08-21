package doph.pipocraft.gui;

import doph.pipocraft.system.Ref;
import doph.pipocraft.tileentity.TileEntityTransferMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
        case Ref.GUI_TRANSFER_MACHINE:
            return new ContainerTransferMachine(player.inventory,
                    (TileEntityTransferMachine) world.getTileEntity(new BlockPos(x, y, z)), player);
        default:
            return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
        case Ref.GUI_TRANSFER_MACHINE:
            return new GuiContainerTransferMachine(player, world, x, y, z);
        default:
            return null;
        }
    }

}
