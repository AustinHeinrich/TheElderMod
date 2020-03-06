package kobolds.the_elder.init;

import javax.annotation.Nullable;

import kobolds.the_elder.world.WorldProviderElder;
import kobolds.the_elder.world.WorldTypeElder;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;

public class ModWorldGen {
	public static final int ELDER_DIM_ID = findFreeDimensionId();
	public static final DimensionType ELDER_DIM_TYPE = DimensionType.register("Elder", "_elder", ELDER_DIM_ID, WorldProviderElder.class, true);
    
    public static final void registerDimensions() {
    	DimensionManager.registerDimension(ELDER_DIM_ID, ELDER_DIM_TYPE);
    }
    
    @Nullable
    private static Integer findFreeDimensionId() {
        for (int i=2; i<Integer.MAX_VALUE; i++)
        {
            if (!DimensionManager.isDimensionRegistered(i))
            {
                return i;
            }
        }
        
        System.out.println("ERROR: Could not find free dimension ID");
        return null;
    }
}
