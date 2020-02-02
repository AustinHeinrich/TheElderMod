package theElderMod.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import theElderMod.init.ModBlocks;
import theElderMod.init.ModWorldGen;

public class BiomeGarden extends Biome {
	protected static final IBlockState BEDROCK = ModBlocks.ELDER_STONE.getDefaultState();
	
	public BiomeGarden() {
		super(new BiomeProperties(ModWorldGen.ELDER_NAME)
                .setBaseHeight(1.0F)
                .setHeightVariation(1.0F)
                .setTemperature(1.0F));
			
		topBlock = ModBlocks.ELDER_DIRT.getDefaultState();
		fillerBlock = ModBlocks.ELDER_STONE.getDefaultState();
	}
	
	//TODO actually build a biome
}
