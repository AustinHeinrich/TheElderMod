package kobolds.the_elder.items;

import kobolds.the_elder.Elder;
import kobolds.the_elder.commands.util.Teleport;
import kobolds.the_elder.init.ModDimensions;
import kobolds.the_elder.init.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ElderTeleporter extends Item {
	public ElderTeleporter() {
		super();
		this.setCreativeTab(Elder.ELDER_TAB);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack item =  playerIn.getHeldItem(handIn);
		/*
		int dim = ModWorldGen.ELDER_DIM_ID;
		playerIn.changeDimension(dim);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		*/
		if (!worldIn.isRemote) {
			int playerX = playerIn.getPosition().getX();
			int playerY = playerIn.getPosition().getY();
			int playerZ = playerIn.getPosition().getZ();
			double destX ;
			double destY;
			double destZ;

			int dimensionID;
			if (playerIn.dimension == ModDimensions.ELDER.getId()) {
				// if the player is in the Elder, teleport them to the overworld
				dimensionID = 0;
				destX = playerX;
				destZ = playerZ;
				// need to calculate destY
				destY = playerY;
			} else { // if the player is not in the elder, teleport them to the Elder spawn
				dimensionID = ModDimensions.ELDER.getId();
				destX = 0.0D;
				destZ = 0.0D;
				destY = playerIn.posY-15;//40;
			}

			Teleport.teleportToDimension(playerIn, dimensionID, destX, destY, destZ);

			System.out.println("First dim tp----------------------------------------------");


            while (!playerIn.isInsideOfMaterial(Material.AIR)) {
                // current position not valid - increment up one block
                playerIn.setPositionAndUpdate(playerIn.posX, playerIn.posY+1, playerIn.posZ);
            }

            System.out.println("Exited while loop");

			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
	}
}
