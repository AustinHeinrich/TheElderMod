package kobolds.the_elder.world.dimension.elder;

import java.util.List;

import kobolds.the_elder.init.ModBiomes;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

public class BiomeProviderElder extends BiomeProvider { 
	public BiomeProviderElder() {
		List<Biome> spawns = getBiomesToSpawnIn();
		spawns.clear();
		spawns.add(ModBiomes.GARDEN);
		
	}
}
