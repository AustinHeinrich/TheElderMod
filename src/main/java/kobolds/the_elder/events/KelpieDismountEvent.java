package kobolds.the_elder.events;

import kobolds.the_elder.entities.EntityKelpie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KelpieDismountEvent {

    @SubscribeEvent
    public void noDismount(EntityMountEvent event) {
        if (event.getEntityMounting().isDead || event.getEntityBeingMounted().isDead) {
            return;
        }

        if (event.isDismounting() && event.getEntityBeingMounted() instanceof EntityKelpie && event.getEntityMounting() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntityMounting();

            // do not cancel the event if player is in creative
            event.setCanceled(!player.isCreative());
        }
    }

}
