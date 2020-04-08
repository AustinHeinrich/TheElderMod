package kobolds.the_elder.init;

import kobolds.the_elder.blocks.base.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import kobolds.the_elder.blocks.*;

import java.util.ArrayList;
import java.util.List;

//@ObjectHolder(Elder.MODID)
public class ModBlocks  {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // alphanumeric ordering
    public static final Block COLD_IRON_ORE = new OreBlockBase("cold_iron_ore");
    public static final Block ELDER_DIRT = new DirtBlockBase("elder_dirt", Material.GROUND);
    public static final Block ELDER_LEAVES = new LeavesBlockBase("elder_leaves");
    public static final Block ELDER_SAPLING = new SaplingBlockBase("elder_sapling");
    public static final Block ELDER_STONE = new ElderStone("elder_stone", Material.ROCK);
    public static final Block ELDER_WOOD = new WoodBlockBase("elder_wood");
    public static final Block ELDER_WOOD_PLANKS = new BlockBase("elder_wood_planks", Material.WOOD);
    public static final Block ELDER_WOOD_STAIRS = new StairsBlockBase("elder_wood_stairs", ModBlocks.ELDER_WOOD_PLANKS.getDefaultState());
    public static final Block KELPIE_TAR_LAYER = new KelpieTarLayer("kelpie_tar_layer", Material.CARPET);
    public static final Block PACTMAKER_NIGHT_SAFETY = new PactmakerNightSafety("pactmaker_night_safety", Material.REDSTONE_LIGHT);
}
