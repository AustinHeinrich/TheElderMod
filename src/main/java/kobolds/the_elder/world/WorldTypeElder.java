package kobolds.the_elder.world;

import kobolds.the_elder.init.ModWorldGen;
import kobolds.the_elder.world.gen.generators.ChunkGeneratorElder;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeElder extends WorldType {

	public WorldTypeElder() {
		super("Elder");
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
