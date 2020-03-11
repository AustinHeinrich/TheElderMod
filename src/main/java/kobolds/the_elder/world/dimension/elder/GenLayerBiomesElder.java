package kobolds.the_elder.world.dimension.elder;

import kobolds.the_elder.init.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesElder extends GenLayer {
	private Biome[] closeBiomes = {ModBiomes.ELDER_DIMENSION};
	private Biome[] farBiomes = {ModBiomes.FAR};
	
	public GenLayerBiomesElder(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);
		int point;
		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				point = dx + dz*width;
				this.initChunkSeed(dx + x, dz + z);
				if ( x+dx == 0 && z+dz == 0 ) {
					dest[point] = Biome.getIdForBiome(ModBiomes.GARDEN);
				} else if (Math.abs(x+dx) <= 2 && Math.abs(z+dz) <= 2) {
					dest[point] = Biome.getIdForBiome(this.closeBiomes[nextInt(this.closeBiomes.length)]);
				} else if (Math.abs(x+dx) <= 4 && Math.abs(z+dz) <= 4) {
					dest[point] = Biome.getIdForBiome(this.farBiomes[nextInt(this.farBiomes.length)]);
				} else {
					dest[point] = Biome.getIdForBiome(ModBiomes.BEYOND);
				}
			}
		}
		return dest;
	}
}
