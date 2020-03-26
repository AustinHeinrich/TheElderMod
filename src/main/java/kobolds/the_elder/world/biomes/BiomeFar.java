package kobolds.the_elder.world.biomes;

import net.minecraft.world.biome.Biome;

public class BiomeFar extends Biome {

	public BiomeFar() {
        super(new BiomeProperties("Far")
        		.setBaseHeight(0.001F)
        		.setHeightVariation(0.01F)
        		.setTemperature(0.6F)
        		.setRainDisabled()
        		.setWaterColor(12345678));
        this.decorator.treesPerChunk = 7;
    }
}
