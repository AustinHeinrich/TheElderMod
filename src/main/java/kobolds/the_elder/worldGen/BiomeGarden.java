package kobolds.the_elder.worldGen;

import kobolds.the_elder.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

public class BiomeGarden extends Biome {
	
	public BiomeGarden() {
		super(new BiomeProperties("Garden").setBaseBiome("plains").setRainDisabled().setWaterColor(3138975));

		//topBlock = ModBlocks.ELDER_DIRT.getDefaultState();
		//fillerBlock = ModBlocks.ELDER_DIRT.getDefaultState();
	}

}
