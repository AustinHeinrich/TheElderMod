package kobolds.the_elder;

import kobolds.the_elder.blocks.ElderStone;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import kobolds.the_elder.blocks.ElderWood;
import kobolds.the_elder.items.BookOfBefore;

@EventBusSubscriber(modid = Elder.MODID)
public class EventSubscriber {

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        final Block[] blocks = {
                RegistryUtil.setBlockName(new ElderStone(), "elder_stone"),
                RegistryUtil.setBlockName(new ElderWood(), "elder_wood"),
        };

        event.getRegistry().registerAll(blocks);
    }

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        final Item[] items = {
                RegistryUtil.setItemName(new BookOfBefore(), "book_of_before").setCreativeTab(CreativeTabs.MISC),
        };

        final Item[] itemBlocks = {
                new ItemBlock(ModBlocks.ELDER_STONE).setRegistryName(ModBlocks.ELDER_STONE.getRegistryName()),
                new ItemBlock(ModBlocks.ELDER_WOOD).setRegistryName(ModBlocks.ELDER_WOOD.getRegistryName()),
        };

        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
    }
}
