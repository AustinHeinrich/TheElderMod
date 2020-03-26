package kobolds.the_elder.init;

import kobolds.the_elder.blocks.base.BlockBase;
import kobolds.the_elder.blocks.base.OreBlockBase;
import kobolds.the_elder.blocks.base.StairsBlockBase;
import kobolds.the_elder.blocks.base.WoodBlockBase;
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
    public static final OreBlockBase COLD_IRON_ORE = new OreBlockBase("cold_iron_ore");
    public static final ElderDirt ELDER_DIRT = new ElderDirt();
    public static final ElderLeaves ELDER_LEAVES = new ElderLeaves();
    public static final ElderSapling ELDER_SAPLING = new ElderSapling();
    public static final ElderStone ELDER_STONE = new ElderStone();
    public static final WoodBlockBase ELDER_WOOD = new WoodBlockBase("elder_wood");
    public static final BlockBase ELDER_WOOD_PLANKS = new BlockBase("elder_wood_planks", Material.WOOD);
    public static final StairsBlockBase ELDER_WOOD_STAIRS = new StairsBlockBase(ELDER_WOOD_PLANKS.getDefaultState(), "elder_wood_stairs");
    public static final KelpieTarLayer KELPIE_TAR_LAYER = new KelpieTarLayer();
    public static final PactmakerNightSafety PACTMAKER_NIGHT_SAFETY = new PactmakerNightSafety();
}
