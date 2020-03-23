package kobolds.the_elder.world.dimension.elder;

import kobolds.the_elder.init.DimensionInit;
import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.world.dimension.elder.ChunkGeneratorElder;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DimensionElder extends WorldProvider {

    @Override
    public void init() {
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderElder(this.world.getSeed());
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionInit.ELDER;
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

        float f1 = 1.0F - (float)((Math.cos((double)f * 3.141592653589793D) + 1.0D) / 2.0D);
        f += (f1 - f) / 3.0F;
        return f;
    }

    @Override
    public boolean canRespawnHere() {

        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }


}
