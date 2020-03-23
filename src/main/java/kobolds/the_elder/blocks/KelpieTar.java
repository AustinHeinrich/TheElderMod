package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class KelpieTar extends Block {

    public KelpieTar() {
        super(Material.CRAFTED_SNOW);
        this.setTickRandomly(true);
        this.setCreativeTab(Elder.ELDER_TAB);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        // todo

        return null;
    }

}
