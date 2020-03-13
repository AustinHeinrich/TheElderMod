package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;

public class ColdIronOre extends BlockOre {

    public ColdIronOre() {
        setCreativeTab(Elder.ELDER_TAB);
        setSoundType(SoundType.STONE);
    }
}
