package kobolds.the_elder.init;

import javax.annotation.Nullable;

import kobolds.the_elder.world.dimension.elder.DimensionElder;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModWorldGen {
	public static final int ELDER_DIM_ID = findFreeDimensionId();
	public static final DimensionType ELDER_DIM_TYPE = DimensionType.register("Elder", "_elder", ELDER_DIM_ID, DimensionElder.class, true);
    
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
