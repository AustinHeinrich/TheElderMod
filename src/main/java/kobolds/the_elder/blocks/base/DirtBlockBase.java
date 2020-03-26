package kobolds.the_elder.blocks.base;

import javafx.scene.paint.Material;
import kobolds.the_elder.Elder;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;

public class DirtBlockBase extends BlockDirt implements IHasModel {
	
	public DirtBlockBase(String name) {
		super();
		setRegistryName(name);
		setUnlocalizedName(Elder.MODID + "." + name);

		setCreativeTab(Elder.ELDER_TAB);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Elder.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
