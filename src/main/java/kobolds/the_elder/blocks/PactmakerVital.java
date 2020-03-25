package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {

            // do stuff

            return true;
        }

        return false;
    }
}
