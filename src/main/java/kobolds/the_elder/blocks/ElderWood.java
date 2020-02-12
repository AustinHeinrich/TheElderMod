package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ElderWood extends Block {

    public ElderWood() {
        super(Material.WOOD);
        setCreativeTab(Elder.ELDER_TAB);
        setSoundType(SoundType.WOOD);
    }

}
