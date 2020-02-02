package theElderMod.worldgen;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeProviderSingle;
import theElderMod.init.ModBiomes;

public class BiomeProviderElder extends BiomeProviderSingle {
	
	public BiomeProviderElder() {
		super(ModBiomes.GARDEN);
	}
	
	public BiomeDecorator createBiomeDecorator() {
		BiomeDecorator decorator = new BiomeDecoratorGarden();
		
		// TODO Include additions to BiomeDecorator
		
		return decorator;
	}
}
