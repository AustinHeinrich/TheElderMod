package kobolds.the_elder.potions;

import kobolds.the_elder.Elder;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

// dummy potion, but makes player immune to the effects of night in the Elder
public class ElderNightImmunity extends Potion {

    public ElderNightImmunity(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
        super(isBadEffectIn, liquidColorIn);
        setPotionName("effect." + name);
        setRegistryName(new ResourceLocation(Elder.MODID + ":" + name));
    }
}
