package kobolds.the_elder.items.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModItems;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.item.ItemSnowball;

public abstract class ItemThrownBase extends ItemSnowball implements IHasModel {

    public ItemThrownBase(String name) {
        setRegistryName(Elder.MODID, name);
        setUnlocalizedName(Elder.MODID + "." + name);
        setCreativeTab(Elder.ELDER_TAB);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Elder.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
