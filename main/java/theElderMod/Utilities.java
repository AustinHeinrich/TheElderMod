package theElderMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/***
 * This class exists to contain shortcut functions for operations that are written many times
 */
public class Utilities {
	
	public static Item setItemName(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(name);
		return item;
	}
	
	public static Block setBlockName(Block block, String name) {
		block.setUnlocalizedName(name);
		block.setRegistryName(name);
		return block;
	}
}
