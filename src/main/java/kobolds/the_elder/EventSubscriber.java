package kobolds.the_elder;

import kobolds.the_elder.blocks.*;
import kobolds.the_elder.commands.CommandDimensionTeleport;
import kobolds.the_elder.init.*;
import kobolds.the_elder.items.ElderTeleporter;
import kobolds.the_elder.util.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import kobolds.the_elder.blocks.ElderWood;
import kobolds.the_elder.items.BookOfBefore;

@EventBusSubscriber(modid = Elder.MODID)
public class EventSubscriber {

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {

        // registering this up here so its default state can be used by the stairs/other elder wood blocks
        ElderWoodPlanks elder_wood_planks = new ElderWoodPlanks();

        final Block[] blocks = {
                RegistryUtil.setBlockName(new ColdIronOre(), "cold_iron_ore"),
                RegistryUtil.setBlockName(new ElderDirt(), "elder_dirt"),
                        RegistryUtil.setBlockName(new ElderLeaves(), "elder_leaves"),
                        RegistryUtil.setBlockName(new ElderSapling(), "elder_sapling"),
                        RegistryUtil.setBlockName(new ElderStone(), "elder_stone"),
                RegistryUtil.setBlockName(new ElderWood(), "elder_wood"),
                RegistryUtil.setBlockName(elder_wood_planks, "elder_wood_planks"),
                RegistryUtil.setBlockName(new ElderWoodStairs(elder_wood_planks.getDefaultState()), "elder_wood_stairs"),
        };

        event.getRegistry().registerAll(blocks);
    }

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        final Item[] items = {
                RegistryUtil.setItemName(new BookOfBefore(), "book_of_before").setCreativeTab(CreativeTabs.MISC),
                RegistryUtil.setItemName(new ElderTeleporter(), "elder_teleporter").setCreativeTab(CreativeTabs.MISC),
        };

        final Item[] itemBlocks = {
                new ItemBlock(ModBlocks.COLD_IRON_ORE).setRegistryName(ModBlocks.COLD_IRON_ORE.getRegistryName()),
                        new ItemBlock(ModBlocks.ELDER_LEAVES).setRegistryName(ModBlocks.ELDER_LEAVES.getRegistryName()),
                        new ItemBlock(ModBlocks.ELDER_STONE).setRegistryName(ModBlocks.ELDER_STONE.getRegistryName()),
                new ItemBlock(ModBlocks.ELDER_DIRT).setRegistryName(ModBlocks.ELDER_DIRT.getRegistryName()),
                        new ItemBlock(ModBlocks.ELDER_SAPLING).setRegistryName(ModBlocks.ELDER_SAPLING.getRegistryName()),
                        new ItemBlock(ModBlocks.ELDER_WOOD).setRegistryName(ModBlocks.ELDER_WOOD.getRegistryName()),
                new ItemBlock(ModBlocks.ELDER_WOOD_PLANKS).setRegistryName(ModBlocks.ELDER_WOOD_PLANKS.getRegistryName()),
                new ItemBlock(ModBlocks.ELDER_WOOD_STAIRS).setRegistryName(ModBlocks.ELDER_WOOD_STAIRS.getRegistryName()),

        };

        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        ModBiomes.registerBiomes();
        DimensionInit.registerDimensions();
        ModWorldGen.registerCustomStructures();

        EventInit.registerEvents();
    }

    public static void serverRegistries(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandDimensionTeleport());
    }
}
