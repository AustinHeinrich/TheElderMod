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
import theElderMod.blocks.BlockElderDirt;
import theElderMod.blocks.BlockElderStone;

public class ModBlocks {
	public static final BlockElderStone ELDER_STONE = null;
	public static final BlockElderDirt 	ELDER_DIRT  = null;
	
	@ObjectHolder("elder_stone")
	public static final ItemBlock ITEM_BLOCK_ELDER_STONE = null;
	
	@ObjectHolder("elder_stone")
	public static final ItemBlock ITEM_BLOCK_ELDER_DIRT  = null;
	
	@EventBusSubscriber(modid = MainMod.MODID)
	public static class RegistrationHandler {
	}
}
