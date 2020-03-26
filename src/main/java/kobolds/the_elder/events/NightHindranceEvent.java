package kobolds.the_elder.events;

import kobolds.the_elder.init.ModDimensions;
import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.init.ModPotions;
import kobolds.the_elder.world.dimension.elder.DimensionElder;

import net.minecraft.client.Minecraft;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class NightHindranceEvent {

    // send a message when the player enters the Dimension
    @SubscribeEvent
    public void elderWarning(PlayerEvent.PlayerChangedDimensionEvent event) {
        DimensionElder elderDim = new DimensionElder();

        if (event.player.dimension == ModDimensions.getDimID()) {
            ITextComponent entryMsg = new TextComponentString("Leave not the Garden at night.");
            event.player.sendMessage(entryMsg);
        }
    }

    // hinder the player at night in the Elder
    @SubscribeEvent
    public void elderNight(TickEvent.PlayerTickEvent event) {
            int nightTick = 13000; // same as "/time set night"
            BlockPos pos = new BlockPos(event.player.getPositionVector());
            PotionEffect potEff = new PotionEffect(MobEffects.WITHER, 500, 2);

            // at night, give the player certain effects to hinder them
            if (!event.player.isCreative() && !event.player.isPotionActive(ModPotions.ELDER_NIGHT_IMMUNITY)
                    && event.player.dimension == ModDimensions.getDimID()) {
                if (event.player.world.getBiome(pos) != ModBiomes.GARDEN && event.player.world.getWorldTime() > nightTick) {
                    event.player.addPotionEffect(potEff);
                }
            }
        }
}
