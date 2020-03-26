package kobolds.the_elder.items.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ItemBase extends Item implements IHasModel {

    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Elder.ELDER_TAB);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Elder.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
