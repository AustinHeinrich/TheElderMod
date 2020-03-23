package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class ElderWoodStairs extends BlockStairs {

    public ElderWoodStairs(IBlockState modelState) {
        super(modelState);
        setCreativeTab(Elder.ELDER_TAB);
        setSoundType(SoundType.WOOD);

        this.useNeighborBrightness = true;
    }
}
