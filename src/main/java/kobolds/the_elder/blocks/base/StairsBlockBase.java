package kobolds.the_elder.blocks.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class StairsBlockBase extends BlockStairs {

    public StairsBlockBase(IBlockState modelState, String name) {
        super(modelState);

        this.useNeighborBrightness = true;

        setRegistryName(name);
        setUnlocalizedName(Elder.MODID + "." + name);
        setCreativeTab(Elder.ELDER_TAB);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
