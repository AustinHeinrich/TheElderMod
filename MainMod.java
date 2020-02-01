package theElderMod;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MainMod.MODID, name = MainMod.NAME, version = MainMod.VERSION, 
acceptedMinecraftVersions = MainMod.MC_VERSION)
public class MainMod {

	public static final String MODID = "theElder";
	public static final String NAME = "The Elder";
	public static final String VERSION = "0.0.1";
	public static final String MC_VERSION = "[1.12.2]";

	public static final Logger LOGGER = LogManager.getLogger(MainMod.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info(MainMod.NAME + "says hi!");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        
	}

}