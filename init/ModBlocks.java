package theElderMod.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;
import theElderMod.MainMod;
import theElderMod.Utilities;
import theElderMod.blocks.BlockElderStone;

public class ModBlocks {
	public static final BlockElderStone ELDER_STONE = null;
	
	@ObjectHolder("elder_stone")
	public static final ItemBlock ITEM_BLOCK_ELDER_STONE = null;
	
	@EventBusSubscriber(modid = MainMod.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void onEvent(final RegistryEvent.Register<Block> event) {
			final Block[] blocks = {
					Utilities.setBlockName(new BlockElderStone(), "elder_stone")
			};
			
			event.getRegistry().registerAll(blocks);
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			final Item[] items = {
					Utilities.setItemName(new ItemBlock(ELDER_STONE), ELDER_STONE.getRegistryName().getResourcePath())
			};
			
			event.getRegistry().registerAll(items);
		}
	}
}
