package doph.pipocraft.block;

import java.util.Random;

import doph.pipocraft.init.PCCreativeTabs;
import doph.pipocraft.init.PCItems;
import doph.pipocraft.system.Utils;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockGachaOre extends BlockOre {
    public BlockGachaOre() {
        super();
        this.setCreativeTab(PCCreativeTabs.TAB_PIPOCRAFT);
        Utils.getInstance().setName(this, "gacha_ore");
        this.setHardness(4.0f);
        this.setResistance(5.0f);
        this.setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return PCItems.gachachip;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }
}