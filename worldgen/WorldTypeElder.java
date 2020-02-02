package theElderMod.worldgen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;
import theElderMod.init.ModWorldGen;

public class WorldTypeElder extends WorldType {

	public WorldTypeElder() {
		super(ModWorldGen.ELDER_NAME);
	}
	
	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderElder();
	}
	
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
