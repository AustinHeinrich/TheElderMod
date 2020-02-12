package kobolds.the_elder.worldGen;

import kobolds.the_elder.init.ModWorldGen;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeElder extends WorldType {

	public WorldTypeElder() {
		super(ModWorldGen.ELDER_NAME);
	}
	
	//@Override
	//public BiomeProvider getBiomeProvider(World world) {
	//	return new BiomeProviderElder();
	//}
	
	@Override 
	public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGeneratorElder(world);
	}
	
	@Override 
	public int getMinimumSpawnHeight(World world) {
		return world.getSeaLevel() + 1;
	}
	
	// TODO: Fill out world type settings

}
