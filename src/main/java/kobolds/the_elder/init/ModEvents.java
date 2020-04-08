package kobolds.the_elder.init;

import kobolds.the_elder.events.KelpieDismountEvent;
import kobolds.the_elder.events.NightHindranceEvent;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents {

    public static void registerEvents() {
        NightHindranceEvent nightEvent = new NightHindranceEvent();
        MinecraftForge.EVENT_BUS.register(nightEvent);
        KelpieDismountEvent dismountEvent = new KelpieDismountEvent();
        MinecraftForge.EVENT_BUS.register(dismountEvent);
    }
}
