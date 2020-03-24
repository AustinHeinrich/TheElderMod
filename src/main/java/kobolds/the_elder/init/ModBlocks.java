package kobolds.the_elder.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import kobolds.the_elder.Elder;
import kobolds.the_elder.blocks.*;

@ObjectHolder(Elder.MODID)
public class ModBlocks  {

    // alphanumeric ordering
    public static final Block COLD_IRON_ORE = null;
    public static final Block ELDER_DIRT = new ElderDirt();
    public static final Block ELDER_LEAVES = null;
    public static final Block ELDER_SAPLING = null;
    public static final Block ELDER_STONE = new ElderStone();
    public static final Block ELDER_WOOD = new ElderWood();
    public static final Block ELDER_WOOD_PLANKS = new ElderWoodPlanks();
    public static final Block ELDER_WOOD_STAIRS = null;

}
