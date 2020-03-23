package kobolds.the_elder.init;

import kobolds.the_elder.events.NightHindranceEventHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventInit {

    public static void registerEvents() {
        NightHindranceEventHandler nightEvent = new NightHindranceEventHandler();
        MinecraftForge.EVENT_BUS.register(nightEvent);
    }

}
