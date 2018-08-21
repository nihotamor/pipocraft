package doph.pipocraft.item;

import com.google.common.collect.Sets;

import doph.pipocraft.init.PCCreativeTabs;
import doph.pipocraft.system.Ref;
import doph.pipocraft.system.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemStunClub extends ItemTool {
    public ItemStunClub() {
        super(-2.0F, 12.0F, Item.ToolMaterial.IRON, Sets.newHashSet());
        this.setMaxDamage(Ref.DURABILITY);
        this.setCreativeTab(PCCreativeTabs.TAB_PIPOCRAFT);
        Utils.getInstance().setName(this,  "stun_club");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 24, 7));
        return true;
    }

    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player){
        return false;
    }
}
