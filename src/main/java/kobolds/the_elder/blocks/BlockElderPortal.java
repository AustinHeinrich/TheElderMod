package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import kobolds.the_elder.commands.util.Teleport;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockElderPortal extends Block {
    public static final AxisAlignedBB END_PORTAL_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

    public BlockElderPortal(Material materialIn, String name)
    {
        super(materialIn);

        setRegistryName(name);
        setUnlocalizedName(Elder.MODID + "." + name);
        setCreativeTab(Elder.ELDER_TAB);

        ModBlocks.BLOCKS.add(this);

        this.setLightLevel(1.0F);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return END_PORTAL_AABB;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.DOWN ? super.shouldSideBeRendered(blockState, blockAccess, pos, side) : false;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    /**
     * Called When an Entity Collided with the Block
     */
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (!worldIn.isRemote && !entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn instanceof EntityPlayer)
        {
            System.out.println("Collided with portal");
            int playerX = entityIn.getPosition().getX();
            int playerY = entityIn.getPosition().getY();
            int playerZ = entityIn.getPosition().getZ();
            double destX = 1; // x of where a core should go
            double destY = 255;
            double destZ = 1; // z of where a core should go

            int dimensionID;
            if (entityIn.dimension == ModDimensions.ELDER.getId())
            {
                // if the player is in the Elder, teleport them to the overworld
                dimensionID = 0;
            }
            else
            {
                // if the player is not in the elder, teleport them to the Elder spawn
                dimensionID = ModDimensions.ELDER.getId();
            }

            // spawn player to load dimension
            Teleport.teleportToDimension((EntityPlayer) entityIn, dimensionID, destX, destY, destZ);
            System.out.println("Changing Dimension");

            // find a suitable location
            BlockPos posBelow;
            for (posBelow = new BlockPos(destX, destY-1, destZ); !worldIn.getBlockState(posBelow).isSideSolid(worldIn, posBelow, EnumFacing.UP) && posBelow.getY() > 0; posBelow = posBelow.down())
            {
                System.out.println("Block: " + worldIn.getBlockState(posBelow).getBlock() + ", pos: " + posBelow.getY());
            }
            // set the player's position to the position above posBelow
            entityIn.setPositionAndUpdate(posBelow.up().getX(), posBelow.up().getY(), posBelow.up().getZ());

            /*
            // if the block below the player is not a portal frame block, generate a portal 1 block below the player's height
            if (entityIn..getBlockState(posBelow).getBlock() != ModBlocks.ELDER_PORTAL_CORE)
            {
                System.out.println("Spawning portal");
                spawnPortal(worldIn, posBelow.getY());
            }
            */

        }
    }

    // generate a portal at the given height
    public void spawnPortal(World worldIn, int height) {
        // TODO: NON-BRUTE-FORCE SOLUTION - MAKE WORK WITH VARIABLE SIZE PORTALS
        // spawn cores
        worldIn.setBlockState(new BlockPos(1, height, 1), ModBlocks.ELDER_PORTAL_CORE.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(1, height, -2), ModBlocks.ELDER_PORTAL_CORE.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(-2, height, -2), ModBlocks.ELDER_PORTAL_CORE.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(-2, height, 1), ModBlocks.ELDER_PORTAL_CORE.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));

        // spawn frames
        worldIn.setBlockState(new BlockPos(1, height, -1), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(1, height, 0), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(0, height, 0), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(-1, height, 1), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(-2, height, 0), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(-2, height, -1), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(-1, height, -2), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));
        worldIn.setBlockState(new BlockPos(0, height, -2), ModBlocks.ELDER_PORTAL_FRAME.getDefaultState().withProperty(BlockElderPortalFrame.ACTIVE, true));

        // spawn portal blocks
        worldIn.setBlockState(new BlockPos(0, height, -1), ModBlocks.ELDER_PORTAL.getDefaultState());
        worldIn.setBlockState(new BlockPos(0, height, 0), ModBlocks.ELDER_PORTAL.getDefaultState());
        worldIn.setBlockState(new BlockPos(-1, height, 0), ModBlocks.ELDER_PORTAL.getDefaultState());
        worldIn.setBlockState(new BlockPos(-1, height, -1), ModBlocks.ELDER_PORTAL.getDefaultState());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)((float)pos.getX() + rand.nextFloat());
        double d1 = (double)((float)pos.getY() + 0.8F);
        double d2 = (double)((float)pos.getZ() + rand.nextFloat());
        double d3 = 0.0D;
        double d4 = 0.0D;
        double d5 = 0.0D;
        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.BLACK;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     *
     * @return an approximation of the form of the given face
     */
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
