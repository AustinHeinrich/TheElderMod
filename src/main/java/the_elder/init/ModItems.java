package the_elder.init;

import the_elder.Elder;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Elder.MODID)
public class ModItems {
    public static final Item BOOK_OF_BEFORE = null;

    @EventBusSubscriber(modid = Elder.MODID)
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerItems(Register<Item> event) {
            final Item[] items = {
                    new Item()
                            .setRegistryName(Elder.MODID, "book_of_before")
                            .setUnlocalizedName("book_of_before")
                            .setCreativeTab(CreativeTabs.MISC)
            };

            event.getRegistry().registerAll(items);
        }
    }
}
