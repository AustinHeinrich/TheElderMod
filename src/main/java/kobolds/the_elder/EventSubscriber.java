package kobolds.the_elder;

import kobolds.the_elder.blocks.*;
import kobolds.the_elder.blocks.base.BlockBase;
import kobolds.the_elder.blocks.base.OreBlockBase;
import kobolds.the_elder.blocks.base.StairsBlockBase;
import kobolds.the_elder.commands.CommandDimensionTeleport;
import kobolds.the_elder.init.*;
import kobolds.the_elder.items.ElderTeleporter;
import kobolds.the_elder.items.KelpieTarBall;
import kobolds.the_elder.util.RegistryUtil;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import kobolds.the_elder.blocks.base.WoodBlockBase;
import kobolds.the_elder.items.BookOfBefore;
import net.minecraftforge.fml.relauncher.SideOnly;

import static kobolds.the_elder.init.ModBlocks.ELDER_WOOD_PLANKS;

@EventBusSubscriber(modid = Elder.MODID)
public class EventSubscriber {

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        // register each block entry
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }

        for (Block block : ModBlocks.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModels();
            }
        }
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        ModBiomes.registerBiomes();
        ModDimensions.registerDimensions();
        ModWorldGen.registerCustomStructures();
        ModPotions.registerPotions();

        ModEvents.registerEvents();
    }

    public static void serverRegistries(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandDimensionTeleport());
    }
}
