package kobolds.the_elder.events;

import kobolds.the_elder.init.DimensionInit;
import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.world.dimension.elder.DimensionElder;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class NightHindranceEventHandler {

    // send a message when the player enters the Dimension
    @SubscribeEvent
    public void elderWarning(PlayerEvent.PlayerChangedDimensionEvent event) {
        DimensionElder elderDim = new DimensionElder();

        if (event.player.dimension == DimensionInit.getDimID()) {
            ITextComponent entryMsg = new TextComponentString("Leave not the Garden at night.");
            event.player.sendMessage(entryMsg);
        }
    }

    // hinder the player at night in the Elder
    @SubscribeEvent
    public void elderNight(PlayerEvent event) {
        BlockPos pos = new BlockPos(event.player.getPositionVector());

        // at night (13000 is the same as /time set night), give the player certain effects to hinder the,
        if (!event.player.isCreative() && event.player.dimension == DimensionInit.getDimID()) {
            if (event.player.world.getBiome(pos) != ModBiomes.GARDEN && event.player.world.getWorldTime() > 13000) {
                event.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 500, 2));
            }
        }
    }
}
