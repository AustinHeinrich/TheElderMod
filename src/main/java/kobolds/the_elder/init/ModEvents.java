package kobolds.the_elder.init;

import kobolds.the_elder.events.NightHindranceEvent;
import kobolds.the_elder.events.TarBallHitEvent;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents {

    public static void registerEvents() {
        NightHindranceEvent nightEvent = new NightHindranceEvent();
        TarBallHitEvent tarBallEvent = new TarBallHitEvent();
        MinecraftForge.EVENT_BUS.register(nightEvent);
        MinecraftForge.EVENT_BUS.register(tarBallEvent);
    }

}
