package kobolds.the_elder.world.biomes;

import kobolds.the_elder.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeHell;

public class ElderBiomeBase extends Biome {
	protected int grassColor = 0x70eeb0;
	protected int foliageColor = 0x974588;
	protected int skyColor = 0xfffb77;
	
	public ElderBiomeBase (BiomeProperties properties) {
		super(properties);
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = ModBlocks.ELDER_DIRT.getDefaultState();
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
