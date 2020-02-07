package theElderMod.init;

import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import theElderMod.MainMod;
import theElderMod.worldgen.BiomeGarden;

@ObjectHolder(MainMod.MODID)
public class ModBiomes {
	public final static BiomeGarden GARDEN = null;
	
	public static void initBiomeManagerDictionary() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(GARDEN, 10));
	}
}
