package kobolds.the_elder.world.biomes;

import net.minecraft.world.biome.Biome;

public class BiomeBeyond extends ElderBiomeBase {

	public BiomeBeyond() {
        super(new BiomeProperties("Beyond")
        		.setBaseHeight(-5F)
        		.setHeightVariation(0F)
        		.setTemperature(0.6F)
        		.setRainDisabled()
        		);
	}
}
