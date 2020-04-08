package kobolds.the_elder.items;

import kobolds.the_elder.client.gui.GUIBookOfBefore;
import kobolds.the_elder.items.base.ItemBase;

import kobolds.the_elder.Elder;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
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

    // on right click, display the book
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Minecraft.getMinecraft().displayGuiScreen(new GUIBookOfBefore());

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
