package kobolds.the_elder.init;

import kobolds.the_elder.Elder;
import kobolds.the_elder.world.dimension.elder.DimensionElder;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class DimensionInit {

    public static final DimensionType ELDER = DimensionType.register("Elder", "_elder", 2, DimensionElder.class, false);

    public static void registerDimensions() {
        DimensionManager.registerDimension(2, ELDER);
    }
}
