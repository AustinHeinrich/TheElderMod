package the_elder.items;

import net.minecraft.creativetab.CreativeTabs;
        import net.minecraft.item.Item;

/*
  BookOfBefore is a book the player can right-click to gain info about the mod

  TODO make this book -- need a gui handler, I think?
 */
public class BookOfBefore extends Item {
    // aka the Elder aka the Land Before Time feat. Littlefoot's mom

    public BookOfBefore() {
        super();
        setCreativeTab(CreativeTabs.MISC);
    }
}
