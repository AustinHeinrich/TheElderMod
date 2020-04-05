package kobolds.the_elder.client;

import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Map;

//@EventBusSubscriber(value = Side.CLIENT, modid = Elder.MODID)
public class ModelRegistrationHandler {

    //@SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        IStateMapper stateMapper = new DefaultStateMapper();

        // blocks
        registerBlock(ModBlocks.COLD_IRON_ORE, stateMapper);
        registerBlock(ModBlocks.ELDER_DIRT, stateMapper);
        registerBlock(ModBlocks.ELDER_STONE, stateMapper);
        registerBlock(ModBlocks.ELDER_WOOD, stateMapper);
        registerBlock(ModBlocks.ELDER_WOOD_PLANKS, stateMapper);
        registerBlock(ModBlocks.ELDER_WOOD_STAIRS, stateMapper);
        registerBlock(ModBlocks.KELPIE_TAR_LAYER, stateMapper);

        // items
        registerItemModel(ModItems.BOOK_OF_BEFORE, 0);
        registerItemModel(ModItems.ELDER_TELEPORTER, 0);
    }

    private static void registerBlock(Block block, IStateMapper stateMapper) {
        Map<IBlockState, ModelResourceLocation> stateMRLMap = stateMapper.putStateModelLocations(block);

        // itemblock
        registerItemModel(Item.getItemFromBlock(block), 0);
    }

    private static void registerItemModel(Item item, int meta) {

        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
