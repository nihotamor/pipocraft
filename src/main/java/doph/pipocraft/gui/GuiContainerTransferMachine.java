package doph.pipocraft.gui;

import java.io.IOException;

import doph.pipocraft.init.PCItems;
import doph.pipocraft.system.Ref;
import doph.pipocraft.tileentity.TileEntityTransferMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiContainerTransferMachine extends GuiContainer {

    private IInventory inventory;
    private InventoryPlayer playerInv;
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/trm.png");

    public GuiContainerTransferMachine(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerTransferMachine(player.inventory,
                (TileEntityTransferMachine) world.getTileEntity(new BlockPos(x, y, z)), player));
        playerInv = player.inventory;
        this.inventory = (TileEntityTransferMachine) world.getTileEntity(new BlockPos(x, y, z));
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButtonRegister(this.guiLeft, this.guiTop));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == Ref.BUTTON_REGISTER) {
            ItemStack stack = inventory.getStackInSlot(0);
            if(stack.getItem() != PCItems.timeNet) {
                return;
            }
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagCompound tag1 = new NBTTagCompound();
            NBTTagCompound tag2 = new NBTTagCompound();
            tag.setInteger(Ref.TAG_X, inventory.getField(0));
            tag.setInteger(Ref.TAG_Y, inventory.getField(1) + 1);
            tag.setInteger(Ref.TAG_Z, inventory.getField(2));
            tag1.setTag(Ref.TAG_TRANSFER_LOCATION, tag);
            tag2.setTag(Ref.TAG_PIPOCRAFT, tag1);
            if(stack.hasTagCompound()) {
                stack.getTagCompound().setTag(Ref.TAG_PIPOCRAFT, tag1);
            }else {
                stack.setTagCompound(tag2);
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        if (texture != null) {
            this.mc.renderEngine.bindTexture(texture);
            this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96,
                Ref.TEXT_COLOR);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
