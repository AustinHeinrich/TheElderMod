package kobolds.the_elder.world.biomes;

public class BiomeElder extends ElderBiomeBase {

    public BiomeElder() {
        super(new BiomeProperties("Elder")
        		.setBaseHeight(1.5F)
        		.setHeightVariation(1.2F)
        		.setTemperature(0.6F)
        		.setRainDisabled()
        		.setWaterColor(12345678)
        		);
    }
}
