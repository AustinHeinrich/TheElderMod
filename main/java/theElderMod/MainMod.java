package theElderMod;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import theElderMod.init.ModWorldGen;

@Mod(modid = MainMod.MODID, name = MainMod.NAME, version = MainMod.VERSION, 
acceptedMinecraftVersions = MainMod.MC_VERSION)
public class MainMod {

	public static final String MODID = "theElder";
	public static final String NAME = "The Elder";
	public static final String VERSION = "0.0.1";
	public static final String MC_VERSION = "[1.12.2]";

	public static final Logger LOGGER = LogManager.getLogger(MainMod.MODID);

	// TODO Fill out inits
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModWorldGen.registerDimensions();
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModWorldGen.registerWorldGenerators();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
        
	}

}