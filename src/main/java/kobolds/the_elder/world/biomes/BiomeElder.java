package kobolds.the_elder.world.biomes;

import net.minecraft.world.biome.Biome;

public class BiomeElder extends Biome {

    public BiomeElder() {
        super(new BiomeProperties("Elder").setBaseHeight(1.5F).setHeightVariation(1.2F).setTemperature(0.6F).setRainDisabled().setWaterColor(12345678));
    }
}
