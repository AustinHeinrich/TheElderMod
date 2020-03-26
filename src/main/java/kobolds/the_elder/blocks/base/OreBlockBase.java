package kobolds.the_elder.blocks.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;

public class OreBlockBase extends BlockOre {

    public OreBlockBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Elder.ELDER_TAB);
        setSoundType(SoundType.STONE);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
