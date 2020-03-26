package kobolds.the_elder.items;

import kobolds.the_elder.items.base.ItemBase;
import net.minecraft.item.Item;

import kobolds.the_elder.Elder;

/*
  BookOfBefore is a book the player can right-click to gain info about the mod

  TODO make this book -- need a gui handler, I think?
 */
public class BookOfBefore extends ItemBase {
    // aka the Elder aka the Land Before Time feat. Littlefoot's mom

    public BookOfBefore(String name) {
        super(name);
        this.setCreativeTab(Elder.ELDER_TAB);
    }
}
