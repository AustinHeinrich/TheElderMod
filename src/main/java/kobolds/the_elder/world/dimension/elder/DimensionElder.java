package kobolds.the_elder.world.dimension.elder;

import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.init.ModDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProviderEnd;


public class DimensionElder extends WorldProvider {


    @Override
    public void init() {
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderElder(this.world.getSeed());
    }

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.ELDER;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {

        System.out.println("Creating chunk generator for Elder");

        return new ChunkGeneratorElder(world, world.getSeed());
    }

    // controls the celestial spheres and where they appear -- allows separate time in the Elder
    // basically, f= 0.0F is Noon, 0.25F is Sunset, 0.5F is Midnight, 0.75F is sunrise
    // can also extend/shorten day/night cycle by increasing/decreasing the '24000'
    // returns f
    // https://www.minecraftforge.net/forum/topic/30920-the-celestial-model-of-minecraft-including-daynight/
    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        int i = (int)(worldTime % 24000L);
        float f = ((float)i + partialTicks) / 24000.0F - 0.25F;
        if (f < 0.0F) {
            ++f;
        }

        if (f > 1.0F) {
            --f;
        }

        float f1 = 1.0F - (float)((Math.cos((double)f * Math.PI) + 1.0D) / 2.0D);
        f += (f1 - f) / 3.0F;
        return f;
    }

    @Override
    public boolean canRespawnHere() {

        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }
    
    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        BlockPos blockpos = new BlockPos(x, 0, z);

        if ( this.world.getBiome(blockpos).equals(ModBiomes.GARDEN)) {
            return true;
        }
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float time, float f)
    {
        float brightness = MathHelper.cos(time * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        brightness = MathHelper.clamp(brightness, 0.0F, 1.0F);
        float red =  255 / 255;
        float green = 192 / 255F;
        float blue = 255 / 255F;
        red *=   brightness * 0.94F + 0.06F;
        green *= brightness * 0.94F + 0.06F;
        blue *=  brightness * 0.91F + 0.09F;
        return new Vec3d((double)red, (double)green, (double)blue);
    }
}
