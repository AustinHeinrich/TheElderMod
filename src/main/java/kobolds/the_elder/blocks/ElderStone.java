package kobolds.the_elder.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import kobolds.the_elder.Elder;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public class ElderStone extends Block {

    public ElderStone() {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState());
        setCreativeTab(Elder.ELDER_TAB);
        setSoundType(SoundType.STONE);
    }

}
