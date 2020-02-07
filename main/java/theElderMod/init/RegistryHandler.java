package theElderMod.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import theElderMod.Utilities;
import theElderMod.blocks.BlockElderDirt;
import theElderMod.blocks.BlockElderStone;
import theElderMod.items.ItemTemp;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event){
		final Block[] blocks = {
				Utilities.setBlockName(new BlockElderStone(), "elder_stone"),
				Utilities.setBlockName(new BlockElderDirt(), "elder_dirt"),
		};
		
		event.getRegistry().registerAll(blocks);
	}
	
	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		final Item[] items = {
				Utilities.setItemName(new ItemTemp(), "temp_item"),
		};
		final Item[] itemblocks = {
				Utilities.setItemName(new ItemBlock(ModBlocks.ELDER_STONE), ModBlocks.ELDER_STONE.getRegistryName().getResourcePath()),
				Utilities.setItemName(new ItemBlock(ModBlocks.ELDER_DIRT), ModBlocks.ELDER_DIRT.getRegistryName().getResourcePath()),
		};
		
		event.getRegistry().registerAll(items);
		event.getRegistry().registerAll(itemblocks);
	}
}
