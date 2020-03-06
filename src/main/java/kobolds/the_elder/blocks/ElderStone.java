package kobolds.the_elder.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import kobolds.the_elder.Elder;

public class ElderStone extends Block {

    public ElderStone() {
        super(Material.ROCK);
        setCreativeTab(Elder.ELDER_TAB);
        setSoundType(SoundType.STONE);
    }


}
