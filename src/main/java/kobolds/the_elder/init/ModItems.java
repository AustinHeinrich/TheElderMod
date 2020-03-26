package kobolds.the_elder.init;

import kobolds.the_elder.Elder;
import kobolds.the_elder.items.BookOfBefore;
import kobolds.the_elder.items.ElderTeleporter;
import kobolds.the_elder.items.KelpieTarBall;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

//@ObjectHolder(Elder.MODID)
public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item BOOK_OF_BEFORE =       new BookOfBefore("book_of_before");
    public static final Item ELDER_TELEPORTER =     new ElderTeleporter("elder_teleporter");
    public static final Item KELPIE_TAR_BALL =      new KelpieTarBall("kelpie_tar_ball");
}
