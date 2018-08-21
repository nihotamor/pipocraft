package doph.pipocraft.item;

import java.util.Random;

import com.google.common.collect.Sets;

import doph.pipocraft.init.PCBlocks;
import doph.pipocraft.init.PCCreativeTabs;
import doph.pipocraft.system.Ref;
import doph.pipocraft.system.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemTimeNet extends ItemTool {
    public ItemTimeNet() {
        super(-2.0f, 12.0F, Item.ToolMaterial.IRON, Sets.newHashSet());
        this.setMaxDamage(Ref.DURABILITY);
        this.setCreativeTab(PCCreativeTabs.TAB_PIPOCRAFT);
        Utils.getInstance().setName(this, "time_net");
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if(tag != null) {
            tag = tag.getCompoundTag(Ref.TAG_PIPOCRAFT);
            if(tag != null) {
                tag = tag.getCompoundTag(Ref.TAG_TRANSFER_LOCATION);
                if(tag != null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        double x = target.posX;
        double y = target.posY;
        double z = target.posZ;


        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null) {
            tag = tag.getCompoundTag(Ref.TAG_PIPOCRAFT);
            if (tag != null) {
                tag = tag.getCompoundTag(Ref.TAG_TRANSFER_LOCATION);
                if (tag != null) {
                    int px = tag.getInteger(Ref.TAG_X);
                    int py = tag.getInteger(Ref.TAG_Y);
                    int pz = tag.getInteger(Ref.TAG_Z);
                    if (target.getEntityWorld().getBlockState(new BlockPos(px, py - 1, pz))
                            .getBlock() == PCBlocks.transferMachine) {
                        x = px;
                        y = py;
                        z = pz;
                    }
                }
            }
        }
        target.setVelocity(0, 0, 0);
        target.setLocationAndAngles(x + 0.5D, y, z + 0.5D, 0.0F, 0.0F);
        return true;
    }

    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return false;
    }
}
