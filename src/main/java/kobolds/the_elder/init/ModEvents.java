package kobolds.the_elder.init;

import kobolds.the_elder.events.NightHindranceEvent;
import kobolds.the_elder.events.KelpieTarBallHitEvent;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents {

    public static void registerEvents() {
        NightHindranceEvent nightEvent = new NightHindranceEvent();
        KelpieTarBallHitEvent tarBallEvent = new KelpieTarBallHitEvent();
        MinecraftForge.EVENT_BUS.register(nightEvent);
        MinecraftForge.EVENT_BUS.register(tarBallEvent);
    }

}
