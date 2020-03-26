package kobolds.the_elder.blocks.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.block.BlockSapling;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class SaplingBlockBase extends BlockSapling implements IHasModel {

    public SaplingBlockBase(String name) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Elder.ELDER_TAB);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Elder.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
