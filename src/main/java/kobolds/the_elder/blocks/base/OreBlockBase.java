package kobolds.the_elder.blocks.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class OreBlockBase extends BlockOre implements IHasModel {

    public OreBlockBase(String name) {
        setRegistryName(name);
        setUnlocalizedName(Elder.MODID + "." + name);
        setCreativeTab(Elder.ELDER_TAB);
        setSoundType(SoundType.STONE);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public void registerModels() {
        Elder.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
