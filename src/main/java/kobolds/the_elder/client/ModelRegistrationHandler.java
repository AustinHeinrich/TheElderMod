package kobolds.the_elder.client;

import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import kobolds.the_elder.Elder;

@EventBusSubscriber(value = Side.CLIENT, modid = Elder.MODID)
public class ModelRegistrationHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        // items
        registerModel(ModItems.BOOK_OF_BEFORE, 0);

        // blocks
        registerModel(Item.getItemFromBlock(ModBlocks.COLD_IRON_ORE), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.ELDER_DIRT), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.ELDER_STONE), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.ELDER_WOOD), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.ELDER_WOOD_PLANKS), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.ELDER_WOOD_STAIRS), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.KELPIE_TAR_TILE), 0);
    }

    private static void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
