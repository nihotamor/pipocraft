package doph.pipocraft.block;

import doph.pipocraft.init.PCCreativeTabs;
import doph.pipocraft.system.PipoCraft;
import doph.pipocraft.system.Ref;
import doph.pipocraft.system.Utils;
import doph.pipocraft.tileentity.TileEntityTransferMachine;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTransferMachine extends Block {

    public BlockTransferMachine() {
        super(Material.IRON);
        Utils.getInstance().setName(this, "transfer_machine");
        this.setCreativeTab(PCCreativeTabs.TAB_PIPOCRAFT);
        this.setHardness(6.0F);
        this.setResistance(6.0F);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTransferMachine();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
            EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(PipoCraft.INSTANCE, Ref.GUI_TRANSFER_MACHINE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityTransferMachine tile = (TileEntityTransferMachine) worldIn.getTileEntity(pos);
        EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.getStackInSlot(0));
        worldIn.spawnEntity(item);
        super.breakBlock(worldIn, pos, state);
    }
}
