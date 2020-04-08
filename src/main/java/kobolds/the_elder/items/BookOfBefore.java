package kobolds.the_elder.items;

import kobolds.the_elder.blocks.BlockElderPortalFrame;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.items.base.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;

import kobolds.the_elder.Elder;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import kobolds.the_elder.client.gui.GUIBookOfBefore;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

/*
  BookOfBefore is a book the player can right-click to gain info about the mod
 */

public class BookOfBefore extends ItemBase {
    // the Elder Scroll!

    public BookOfBefore(String name) {
        super(name);
        this.setCreativeTab(Elder.ELDER_TAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState blockState = worldIn.getBlockState(pos);
        ItemStack itemStack = player.getHeldItem(hand);



        // If the player can edit here and the core is not active
        if (player.canPlayerEdit(pos.offset(facing), facing, itemStack) && blockState.getBlock() == ModBlocks.ELDER_PORTAL_CORE && !(blockState.getValue(BlockElderPortalFrame.ACTIVE)))
        {
            if (worldIn.isRemote)
            {
                return EnumActionResult.SUCCESS;
            }
            else
            {
                int portalY = pos.getY();
                int sideLength = Elder.PORTAL_SIZE;

                // starts at SW corner and moves along the ring counter clockwise
                BlockPos[] corePositions = {
                        new BlockPos(sideLength / 2 - 1, portalY, sideLength / 2 - 1), // SW
                        new BlockPos(sideLength / 2 - 1, portalY, sideLength / -2),    // NW
                        new BlockPos(sideLength / -2, portalY, sideLength / -2),       // NE
                        new BlockPos(sideLength / -2, portalY, sideLength / 2 - 1)     // SE
                };

                if (verifyPortalConstruction(worldIn, pos, portalY, corePositions))
                {
                    activateBlock(worldIn, blockState, pos);

                    // check if all four cores are activated
                    if (coreActive(worldIn, corePositions[0]) && coreActive(worldIn, corePositions[1]) && coreActive(worldIn, corePositions[2]) && coreActive(worldIn, corePositions[3])) {
                        activatePortal(worldIn, corePositions);
                    }
                }

                return EnumActionResult.SUCCESS;
            }
        }

        return EnumActionResult.FAIL;
    }

    private boolean coreActive(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getValue(BlockElderPortalFrame.ACTIVE);
    }

    // activate the given block
    protected void activateBlock(World worldIn,IBlockState blockState, BlockPos pos)
    {
        Block block = blockState.getBlock();

        if (block == ModBlocks.ELDER_PORTAL_CORE || block == ModBlocks.ELDER_PORTAL_FRAME) {
            worldIn.setBlockState(pos, blockState.withProperty(BlockElderPortalFrame.ACTIVE, true), 2);
            worldIn.updateComparatorOutputLevel(pos, block);
        }
    }

    protected void activatePortal(World worldIn, BlockPos[] corePositions) {
        Block portalBlock = ModBlocks.ELDER_PORTAL;

        // activate frames
        EnumFacing facing = EnumFacing.NORTH;
        int numFramesPerSide = Elder.PORTAL_SIZE - 2;

        BlockPos posWalker = corePositions[0];
        for (BlockPos corePos : corePositions)
        {
            posWalker = corePos;

            for (int i=0; i < numFramesPerSide; i++)
            {
                // move the walker 1 in the facing direction
                posWalker = posWalker.offset(facing);
                // check if the block at the new pos is a frame
                if (worldIn.getBlockState(posWalker).getBlock() == ModBlocks.ELDER_PORTAL_FRAME)
                {
                    activateBlock(worldIn, worldIn.getBlockState(posWalker), posWalker);
                }
            }
            // rotate the current direction clockwise
            facing = facing.rotateYCCW();
            System.out.println(facing);
        }

        System.out.println(facing);
        System.out.println(posWalker.getX() + ", " + posWalker.getY() + ", " + posWalker.getZ());

        for (int i=0; i<numFramesPerSide*numFramesPerSide; i++)
        {
            // rotate
            if (worldIn.getBlockState(posWalker.offset(facing)).getBlock() == ModBlocks.ELDER_PORTAL_FRAME || worldIn.getBlockState(posWalker.offset(facing)).getBlock() == portalBlock)
            {
                facing = facing.rotateYCCW();
            }
            // offset
            posWalker = posWalker.offset(facing);
            // place
            worldIn.setBlockState(posWalker, portalBlock.getDefaultState());
        }
    }

    protected boolean verifyPortalConstruction(World worldIn, BlockPos pos, int portalY, BlockPos[] corePositions) {
        int numFramesPerSide = Elder.PORTAL_SIZE - 2;

        EnumFacing facing = EnumFacing.NORTH;

        for (BlockPos corePos : corePositions) {
            BlockPos posWalker = corePos;
            // check if the block at the core position is a core
            if (worldIn.getBlockState(corePos).getBlock() != ModBlocks.ELDER_PORTAL_CORE) {
                System.out.println("Invalid portal construction - core placement");
                return false;
            }

            System.out.println("Valid core placement at: " + corePos.getX() + ", " + corePos.getY() + ", " + corePos.getZ() + " . Checking Frames...");

            // check the frames
            for (int i = 0; i < numFramesPerSide; i++) {
                // move the walker 1 in the facing direction
                posWalker = posWalker.offset(facing);
                // check if the block at the new pos is a frame
                if (worldIn.getBlockState(posWalker).getBlock() != ModBlocks.ELDER_PORTAL_FRAME) {
                    System.out.println("Invalid frame placement at: " + posWalker.getX() + ", " + posWalker.getY() + ", " + posWalker.getZ());
                    return false;
                }
                System.out.println("Valid frame at: " + posWalker.getX() + ", " + posWalker.getY() + ", " + posWalker.getZ());
            }
            // rotate the current direction clockwise
            facing = facing.rotateYCCW();
        }
        System.out.println("Valid portal construction found.");
        return true;
    }

    // on right click, display the book
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Minecraft.getMinecraft().displayGuiScreen(new GUIBookOfBefore());

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
