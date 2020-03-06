package kobolds.the_elder.items;

import kobolds.the_elder.Elder;
import kobolds.the_elder.commands.util.Teleport;
import kobolds.the_elder.init.ModWorldGen;
import net.minecraft.entity.player.EntityPlayer;
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
		setCreativeTab(Elder.ELDER_TAB);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack item =  playerIn.getHeldItem(handIn);
		/*
		int dim = ModWorldGen.ELDER_DIM_ID;
		playerIn.changeDimension(dim);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		*/
		if (!worldIn.isRemote) {
			Teleport.teleportToDimension(playerIn, 2, playerIn.getPosition().getX(), playerIn.getPosition().getY() + 5, playerIn.getPosition().getZ());
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
	}
}
