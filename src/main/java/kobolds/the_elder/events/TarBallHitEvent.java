package kobolds.the_elder.events;

import kobolds.the_elder.init.ModItems;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TarBallHitEvent {

    @SubscribeEvent
    public void entityHitByTarBall(ProjectileImpactEvent event) {
        System.out.println(event.getEntity().motionX);
    }
}
