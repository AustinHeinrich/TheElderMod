package kobolds.the_elder.util;

// public Java class for storing very useful functions

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import kobolds.the_elder.Elder;

public class RegistryUtil {

    public static Item setItemName(Item anItem, String name) {
        anItem.setRegistryName(Elder.MODID, name);
        anItem.setUnlocalizedName(Elder.MODID + "." + name);
        return anItem;
    }

    public static Block setBlockName(Block aBlock, String name) {
        aBlock.setRegistryName(name);
        aBlock.setUnlocalizedName(Elder.MODID + "." + name);
        return aBlock;
    }
}
