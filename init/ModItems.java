package theElderMod.init;

import theElderMod.MainMod;
import theElderMod.Utilities;
import theElderMod.items.ItemTemp;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(MainMod.MODID)
public class ModItems {

	public static final ItemTemp TEMP_ITEM = null;


	@Mod.EventBusSubscriber(modid = MainMod.MODID)
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerItems(Register<Item> event) {
			final Item[] items = {
					Utilities.setItemName(new ItemTemp(), "temp_item")
			};

			event.getRegistry().registerAll(items);
		}
		
	}
	
}
