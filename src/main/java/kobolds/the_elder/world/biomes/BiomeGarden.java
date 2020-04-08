package kobolds.the_elder.world.biomes;

import kobolds.the_elder.entities.EntityKelpie;
import kobolds.the_elder.entities.EntityWorm;
import kobolds.the_elder.world.gen.ElderTreeGenerator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGarden extends Biome {

	protected static final ElderTreeGenerator TREE = new ElderTreeGenerator(false, true);
	
	public BiomeGarden() {
		super(new BiomeProperties("Garden")
				.setBaseHeight(0.125F)
				.setHeightVariation(0.05F)
				.setTemperature(0.8F)
				.setRainDisabled()
				.setWaterColor(3138975));

		this.topBlock = Blocks.GRASS.getDefaultState();
		this.fillerBlock = Blocks.OBSIDIAN.getDefaultState();

		this.decorator.treesPerChunk = 2;

		// clear the set of mobs which can spawn in the biome - don't want vanilla mobs!
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		// TODO: change this entry to use our actual mobs - this is currently just a test
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWorm.class, 10, 1, 5));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return TREE;
	}
}
