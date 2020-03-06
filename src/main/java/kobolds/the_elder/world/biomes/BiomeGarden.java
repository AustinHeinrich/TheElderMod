package kobolds.the_elder.world.biomes;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;

import java.util.Random;

import kobolds.the_elder.init.ModBlocks;

public class BiomeGarden extends Biome {

	// TODO: figure out what the parameters for this tree class actually need to be
	//protected static final WorldGenAbstractTree TREE = new WorldGenElderTree(false, true);
	
	public BiomeGarden() {
		super(new BiomeProperties("Garden")
				.setBaseHeight(0.125F)
				.setHeightVariation(0.05F)
				.setTemperature(0.8F)
				.setRainfall(0.0F)
				.setRainDisabled()
				.setWaterColor(3138975));

		//topBlock = ModBlocks.ELDER_DIRT.getDefaultState();
		//fillerBlock = ModBlocks.ELDER_DIRT.getDefaultState();

		this.decorator.treesPerChunk = 2;

		// clear the set of mobs which can spawn in the biome - don't want vanilla mobs!
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		// TODO: change this entry to use our actual mobs - this is currently just a test
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 5));
	}

	/*
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return TREE;
	}
	*/
}
