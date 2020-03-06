package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;

public class ElderDirt extends BlockDirt {
	
	public ElderDirt() {
		super();
		setCreativeTab(Elder.ELDER_TAB);
	}
}
