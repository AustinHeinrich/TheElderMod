package kobolds.the_elder.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModItems;

public class ElderTab extends CreativeTabs {

    public ElderTab() {
        super(Elder.MODID + ".eldermod");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.BOOK_OF_BEFORE);
    }
}
