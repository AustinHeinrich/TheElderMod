package kobolds.the_elder.init;

import kobolds.the_elder.potions.ElderNightImmunity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions {
    public static final Potion ELDER_NIGHT_IMMUNITY = new ElderNightImmunity("elder_night_immunity", false, 16777215,0,0);

    public static final PotionType ELDER_NIGHT_IMMUNITY_DEFAULT = new PotionType("elder_night_immunity", new PotionEffect[]{new PotionEffect(ELDER_NIGHT_IMMUNITY, 2400)}).setRegistryName("elder_night_immunity");

    public static void registerPotions() {
        registerPotion(ELDER_NIGHT_IMMUNITY_DEFAULT, ELDER_NIGHT_IMMUNITY);
    }

    public static void registerPotion(PotionType defaultPotion, Potion effect) {
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.registerAll(defaultPotion);
    }

}
