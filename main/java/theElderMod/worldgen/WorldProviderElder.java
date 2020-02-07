package theElderMod.worldgen;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import theElderMod.init.ModWorldGen;

public class WorldProviderElder extends WorldProvider {

    @Override
    public DimensionType getDimensionType()
    {
        return ModWorldGen.ELDER_DIM_TYPE;
    }
    
    // TODO: Fill out world settings
}
