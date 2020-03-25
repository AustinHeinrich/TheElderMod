package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PactmakerVital extends Block {

    public PactmakerVital() {
        super(Material.REDSTONE_LIGHT);
        float hardness = 50f;
        float lightLevel = 0.5f;

        this.setHardness(hardness);
        this.setLightLevel(lightLevel);
        this.setCreativeTab(Elder.ELDER_TAB);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
    }
}
