package kobolds.the_elder.world.dimension.elder;

import kobolds.the_elder.init.DimensionInit;
import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.world.dimension.elder.ChunkGeneratorElder;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionElder extends WorldProvider {

    public void init() {

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

    @Override
    public boolean canRespawnHere() {

        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }
}
