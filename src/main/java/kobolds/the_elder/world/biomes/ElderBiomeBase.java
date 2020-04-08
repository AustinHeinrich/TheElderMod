package kobolds.the_elder.world.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeHell;

public class ElderBiomeBase extends Biome {
	protected int grassColor = 0x90ee90;
	protected int foliageColor = 0x6a7039;
	protected int skyColor = 0xb957ff;
	
	public ElderBiomeBase (BiomeProperties properties) {
		super(properties);
	}

    @SideOnly(Side.CLIENT)
	@Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(grassColor);
    }

    @SideOnly(Side.CLIENT)
	@Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(foliageColor);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float currentTemperature)
    {
        return skyColor;
    }
}
