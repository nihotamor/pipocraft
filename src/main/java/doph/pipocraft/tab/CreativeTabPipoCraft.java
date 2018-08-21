package doph.pipocraft.tab;

import doph.pipocraft.init.PCItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabPipoCraft extends CreativeTabs {
    public CreativeTabPipoCraft() {
        super("pipocraft");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(PCItems.timeNet);
    }
}
