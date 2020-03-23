package kobolds.the_elder.events;

import kobolds.the_elder.entities.EntityKelpieTarBall;
import kobolds.the_elder.items.KelpieTarBall;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KelpieTarBallHitEvent {

    // can this be done in EntityTarBall?

    @SubscribeEvent
    public void onTarBallHit(ProjectileImpactEvent event) {
        if (event.getEntity() instanceof EntityKelpieTarBall) {

        }

    }
}
