package kobolds.the_elder.items;

import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModWorldGen;
import net.minecraft.entity.player.EntityPlayer;
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
		int dim = ModWorldGen.ELDER_DIM_ID;
		if (playerIn.world.provider.getDimension() == dim) {
			dim = 0; // If already in Elder dimension, go to overworld
		}
		playerIn.changeDimension(dim);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);		
	}
}
