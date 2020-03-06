package kobolds.the_elder;

import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.init.ModWorldGen;
import kobolds.the_elder.proxy.ClientProxy;
import kobolds.the_elder.proxy.CommonProxy;
import kobolds.the_elder.tabs.ElderTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Elder.MODID, name = Elder.NAME, version = Elder.VERSION, acceptedMinecraftVersions = Elder.MC_VERSION)
public class Elder
{
    public static final String MODID = "eldermod";  // if this changes, it must be lowercase
    public static final String NAME = "The Elder";
    public static final String VERSION = "0.0.3";
    public static final String MC_VERSION = "[1.12.2]";

    @SidedProxy(clientSide = "kobolds.the_elder.proxy.ClientProxy", serverSide = "kobolds.the_elder.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Elder instance;

    public static final CreativeTabs ELDER_TAB = new ElderTab();

    private static Logger logger = LogManager.getLogger(Elder.MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        EventSubscriber.preInitRegistries(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info(Elder.NAME + "said hi!");
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        EventSubscriber.serverRegistries(event);
    }
}
