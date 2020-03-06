package kobolds.the_elder.init;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import kobolds.the_elder.Elder;
import kobolds.the_elder.blocks.*;

@ObjectHolder(Elder.MODID)
public class ModBlocks  {

    // alphanumeric ordering
    public static final ElderDirt ELDER_DIRT = new ElderDirt();
    public static final ElderLeaves ELDER_LEAVES = null;
    public static final ElderPlanks ELDER_PLANKS = null;
    public static final ElderSapling ELDER_SAPLING = null;
    public static final ElderStone ELDER_STONE = new ElderStone();
    public static final ElderWood ELDER_WOOD = null;

}
