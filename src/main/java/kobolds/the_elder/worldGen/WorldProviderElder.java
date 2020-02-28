package kobolds.the_elder.worldGen;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import kobolds.the_elder.init.ModWorldGen;

public class WorldProviderElder extends WorldProvider {

    @Override
    public DimensionType getDimensionType()
    {
        return ModWorldGen.ELDER_DIM_TYPE;
    }
    
    // TODO: Fill out world settings

}
