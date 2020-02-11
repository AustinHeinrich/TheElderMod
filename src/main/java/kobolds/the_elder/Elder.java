package kobolds.the_elder;

import kobolds.the_elder.tabs.ElderTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Elder.MODID, name = Elder.NAME, version = Elder.VERSION, acceptedMinecraftVersions = Elder.MC_VERSION)
public class Elder
{
    public static final String MODID = "eldermod";  // if this changes, it must be lowercase
    public static final String NAME = "The Elder";
    public static final String VERSION = "0.0.3";
    public static final String MC_VERSION = "[1.12.2]";

    public static final CreativeTabs ELDER_TAB = new ElderTab();

    private static Logger logger = LogManager.getLogger(Elder.MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info(Elder.NAME + "said hi!");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
