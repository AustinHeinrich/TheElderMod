package kobolds.the_elder.world.gen;

import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.world.biomes.BiomeGarden;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WorldGenCustomTrees
{
    private final WorldGenerator ELDER = null; //new WorldGenElderTree();

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.getDimension())
        {
            case 1:
                break;
            case 0:
                runGenerator(ELDER, world, random, chunkX, chunkZ, 3, -1, 0, ModBiomes.GARDEN);
                break;
            case -1:

        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, double chancesToSpawn, int minHeight, int maxHeight, Biome... biomes)
    {
        if (chancesToSpawn < 1)
        {
            if(random.nextDouble() < chancesToSpawn)
                chancesToSpawn = 1;
            else
                chancesToSpawn = 0;
        }

        ArrayList<Biome> biomesList = new ArrayList<Biome>(Arrays.asList(biomes));
        int heightDiff = maxHeight - minHeight + 1;
        for (int i=0; i<chancesToSpawn; i++)
        {
            BlockPos pos = new BlockPos(chunkX * 16 + 10 + random.nextInt(15), minHeight + random.nextInt(heightDiff), chunkZ * 16 + 10 + random.nextInt(15));
            if (minHeight < 0)
                pos = world.getHeight(pos);
            Biome biome = world.provider.getBiomeForCoords(pos);
            if (biomesList.contains(biome) || biomes.length == 0)
                generator.generate(world, random, pos);
        }
    }

}
