package kobolds.the_elder.world.gen;

import java.util.Random;

import kobolds.the_elder.init.ModBlocks;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;;

public class ElderTreeGenerator extends WorldGenAbstractTree {
	//Literally just a Birch Tree with different blocks 
	private static final IBlockState LOG = ModBlocks.ELDER_WOOD.getDefaultState();
    private static final IBlockState LEAF = Blocks.LEAVES.getDefaultState();
    private final boolean useExtraRandomHeight;

    public ElderTreeGenerator(boolean notify, boolean useExtraRandomHeightIn) {
        super(notify);
        this.useExtraRandomHeight = useExtraRandomHeightIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = rand.nextInt(7) + 8;
        
        if (this.useExtraRandomHeight) {
            height += rand.nextInt(7);
        }

        if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getHeight()) {
        	
        	// Checking if there is enough space for a tree to be generated, set flag to false otherwise
            for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
                int space = 1;

                if (j >= position.getY() + height - 1) {
                    space = 2;
                } else if (j == position.getY()) {
                	space = 0;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - space; l <= position.getX() + space; ++l) {
                    for (int i1 = position.getZ() - space; i1 <= position.getZ() + space; ++i1) {
                        if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
                            return false;
                        }
                    }
                }
            }
            
            BlockPos down = position.down();
            IBlockState state = worldIn.getBlockState(down);
            boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling)Blocks.SAPLING);

            if (isSoil && position.getY() < worldIn.getHeight() - height - 1)
            {
                state.getBlock().onPlantGrow(state, worldIn, down, position);
                // Placing Leaves
                for (int leafY = position.getY()+height-8; leafY <= position.getY()+height+2; ++leafY)
                {
                    int y = leafY - (position.getY()+height+2);
                    int rad = 1 - y;
                    
                    if (y != 0) {
                    BlockPos blockpos = new BlockPos(position.getX(), leafY, position.getZ());
                    growLeavesCircle(worldIn, blockpos, Math.min(3, rad));
                    }
                    /*for (int leafX = position.getX() - rad; leafX <= position.getX() + rad; ++leafX)
                    {
                        int x = leafX - position.getX();

                        for (int leafZ = position.getZ() - rad; leafZ <= position.getZ() + rad; ++leafZ)
                        {
                            int z = leafZ - position.getZ();

                            if (Math.abs(x) != rad || Math.abs(z) != rad || rand.nextInt(2) != 0 && y != 0)
                            {
                                BlockPos blockpos = new BlockPos(leafX, leafY, leafZ);
                                IBlockState state2 = worldIn.getBlockState(blockpos);

                                if (state2.getBlock().isAir(state2, worldIn, blockpos)) {
                                    this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                                }
                            }
                        }
                    }*/
                }
                // Placing logs
                for (int logY = 0; logY < height; ++logY) {
                    BlockPos upN = position.up(logY);
                    IBlockState state2 = worldIn.getBlockState(upN);

                    if (state2.getBlock().isAir(state2, worldIn, upN) || state2.getBlock().isLeaves(state2, worldIn, upN)) {
                        this.setBlockAndNotifyAdequately(worldIn, position.up(logY), LOG);
                    }
                }

                return true;
            }
        }
        return false;    
    }
    
    /**
     * grow leaves in a circle with the outsides being within the circle
     */
    protected void growLeavesCircle(World worldIn, BlockPos layerCenter, int width) {
    	
        for (int x = -width; x <= width; x++) {
            for (int z = -width; z <= width; z++) {
                if (x*x + z*z <= width*width) {
                    BlockPos blockpos = layerCenter.add(x, 0, z);
                    IBlockState state = worldIn.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos)) {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                    }
                }
            }
        }
    }
}
