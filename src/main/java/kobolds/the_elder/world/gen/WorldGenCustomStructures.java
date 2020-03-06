// Austin Heinrich
// awh055
// 11177796

package kobolds.the_elder.world.gen;

import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.world.gen.generators.WorldGenStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WorldGenCustomStructures implements IWorldGenerator {

    public static final WorldGenStructure OLD_ADVENTURER_HOUSE = new WorldGenStructure("old_adventurer's_house");
    public static final WorldGenStructure OLD_WAYTOWER = new WorldGenStructure("old_waytower");

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()) {
            case -1:  // the nether
                break;
            case 0:   // the overworld
                generateElderStructures(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                break;
            case 1:   // the end

        }
    }

    // TODO change spawn rate (chance) after alpha
    public void generateElderStructures(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        // chance = 120
        generateStructure(OLD_ADVENTURER_HOUSE, world, random, chunkX, chunkZ, 2, Blocks.GRASS, ModBiomes.GARDEN, Biomes.PLAINS);
        // chance = 30
        generateStructure(OLD_WAYTOWER, world, random, chunkX, chunkZ, 2, Blocks.GRASS, ModBiomes.GARDEN, Biomes.PLAINS);
    }

    // chunkX / chunkZ -- particular chunk loaded
    // chance -- % chance of spawning
    // topBlock -- top layer of the biome eg) a grass block in a Field
    // classes -- biomes the structure can spawn in
    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Biome... biomes) {
       ArrayList<Biome> biomeList = new ArrayList<Biome>(Arrays.asList(biomes)); // making a list out of all biomes

        // calculate appropriate spawn coords within a chunk
        int x = (chunkX * 16) + 8;
        int z = (chunkZ * 16) + 8;
        int y = calculateGenerationHeight(world, x, z, topBlock);
        BlockPos position = new BlockPos(x, y, z);

        // get the biome at the position
        Biome biome = world.provider.getBiomeForCoords(position);

        if (biomeList.contains(biome)) {
            if (random.nextInt(chance) == 0) {
                generator.generate(world, random, position);
            }
        }
    }

    // get the height of the spawn location for the structure
    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
        int y = world.getHeight();
        boolean foundGround = false;

        // if we haven't found the ground yet, continuously reduce y until we do or y hits void
        while (!foundGround && y-- >= 0) {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = block == topBlock;
        }

        return y;
    }
}
