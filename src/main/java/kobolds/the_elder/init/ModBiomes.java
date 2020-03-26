package kobolds.the_elder.init;

import kobolds.the_elder.world.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes {
	public static final Biome GARDEN = new BiomeGarden();
	public static final Biome ELDER_DIMENSION = new BiomeElder();
	public static final Biome FAR = new BiomeFar();
	public static final Biome BEYOND = new BiomeBeyond();
	
	public static void registerBiomes() {

		initBiome(GARDEN, "Garden", BiomeType.WARM, Type.PLAINS, Type.MAGICAL);
		initBiome(ELDER_DIMENSION, "Elder", BiomeType.WARM, Type.SPOOKY, Type.DENSE, Type.DRY);
		initBiome(FAR, "Far", BiomeType.COOL, Type.DENSE);
		initBiome(BEYOND, "Beyond", BiomeType.ICY, Type.VOID);
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 0));
		System.out.println("Biome" + name + "Registered");
		return biome;
	}
}
