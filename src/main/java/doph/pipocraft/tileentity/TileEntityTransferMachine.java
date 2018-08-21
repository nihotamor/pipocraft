package doph.pipocraft.tileentity;

import doph.pipocraft.init.PCItems;
import doph.pipocraft.system.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

public class TileEntityTransferMachine extends TileEntity implements IInventory {
    private NonNullList<ItemStack> itemSet = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    private String customName;

    public TileEntityTransferMachine() {

    }

    @Override
    public String getName() {
        return customName == null ? "" : customName;
    }

    public void setName(String name) {
        this.customName = name;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public boolean hasCustomName() {
        return this.customName == null ? false : true;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return itemSet.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return itemSet.get(0);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        getUpdatePacket();
        //NBTTagCompound tag = new NBTTagCompound();
        //if (itemSet.get(0).hasTagCompound()) {
        //    tag = itemSet.get(0).getTagCompound().copy();
        //}
        ItemStack stack = ItemStackHelper.getAndSplit(this.itemSet, index, count);
        //stack.setTagCompound(tag);
        return stack;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new SPacketUpdateTileEntity(this.pos, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
        super.onDataPacket(net, pkt);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = itemSet.get(0);
        itemSet.set(0, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        itemSet.set(0, stack);
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D,
                    this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey(Ref.TAG_CUSTOMNAME)) {
            customName = compound.getString(Ref.TAG_CUSTOMNAME);
        }

        NBTTagCompound tag = compound.getCompoundTag(Ref.TAG_ITEM);
        if (tag != null) {
            ItemStackHelper.loadAllItems(tag, itemSet);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tag = new NBTTagCompound();
        ItemStackHelper.saveAllItems(tag, itemSet, true);
        compound.setTag(Ref.TAG_ITEM, tag);

        if (this.hasCustomName()) {
            compound.setString(Ref.TAG_CUSTOMNAME, customName);
        }

        return super.writeToNBT(compound);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (itemSet.get(0).getItem().equals(PCItems.timeNet)) {
            return true;
        }
        return false;
    }

    @Override
    public int getField(int id) {
        if (id == 0) {
            return this.pos.getX();
        } else if (id == 1) {
            return this.pos.getY();
        } else if (id == 2) {
            return this.pos.getZ();
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.itemSet.clear();
    }
}
