package kobolds.the_elder.init;

import kobolds.the_elder.blocks.base.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import kobolds.the_elder.Elder;
import kobolds.the_elder.blocks.*;

import java.util.ArrayList;
import java.util.List;

@ObjectHolder(Elder.MODID)
public class ModBlocks  {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // alphanumeric ordering
    public static final OreBlockBase COLD_IRON_ORE =                  new OreBlockBase("cold_iron_ore");
    public static final DirtBlockBase ELDER_DIRT =                    new DirtBlockBase("elder_dirt");
    public static final LeavesBlockBase ELDER_LEAVES =                new LeavesBlockBase("elder_leaves");
    public static final SaplingBlockBase ELDER_SAPLING =              new SaplingBlockBase("elder_sapling");
    public static final ElderStone ELDER_STONE =                      new ElderStone("elder_stone", Material.ROCK);
    public static final WoodBlockBase ELDER_WOOD =                    new WoodBlockBase("elder_wood");
    public static final BlockBase ELDER_WOOD_PLANKS =                 new BlockBase("elder_wood_planks", Material.WOOD);
    public static final StairsBlockBase ELDER_WOOD_STAIRS =           new StairsBlockBase(ELDER_WOOD_PLANKS.getDefaultState(), "elder_wood_stairs");
    public static final KelpieTarLayer KELPIE_TAR_LAYER =             new KelpieTarLayer("kelpie_tar_layer", Material.CLAY);
    public static final PactmakerNightSafety PACTMAKER_NIGHT_SAFETY = new PactmakerNightSafety("pactmaker_night_safety", Material.REDSTONE_LIGHT);
}
