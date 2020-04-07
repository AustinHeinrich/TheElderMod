package kobolds.the_elder.blocks.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class StairsBlockBase extends BlockStairs implements IHasModel {

    public StairsBlockBase(String name, IBlockState modelState) {
        super(modelState);

        this.useNeighborBrightness = true;
        this.setLightOpacity(0);

        setRegistryName(name);
        setUnlocalizedName(Elder.MODID + "." + name);
        setCreativeTab(Elder.ELDER_TAB);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Elder.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
