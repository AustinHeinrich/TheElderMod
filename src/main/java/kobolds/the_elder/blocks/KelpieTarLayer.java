package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import kobolds.the_elder.blocks.base.BlockBase;
import kobolds.the_elder.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


import java.util.Random;

// TODO fix neighbour transparency

public class KelpieTarLayer extends BlockBase {

    protected static final AxisAlignedBB TAR_SIZE = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

    public KelpieTarLayer(String name, Material material) {
        super(name, material);
        float hardness = 1.0f;

        // properties
        this.setHardness(hardness);
        this.setTickRandomly(true);
        this.setSoundType(SoundType.SLIME);
        this.setHardness(hardness);
        this.setCreativeTab(Elder.ELDER_TAB);
    }

    // sets the height of the tar tile
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return TAR_SIZE;
    }

    // allows travel over tile
    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    // allows travel over tile
    @Override
    public boolean isTopSolid(IBlockState state) {
        return false;
    }

    /*
    // allows travel over tile
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    } */

    // drop a tar ball, not the tile
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.KELPIE_TAR_BALL;
    }

    // mobs are stopped, players are slowed a lot
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.setVelocity(0D, 0D, 0D);
        entityIn.velocityChanged = true;
    }

}
