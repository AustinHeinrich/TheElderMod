package kobolds.the_elder.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;

public class EntityAISwimmingConsiderPassenger extends EntityAISwimming {

    private final EntityLiving entity;

    public EntityAISwimmingConsiderPassenger(EntityLiving entityIn) {
        super(entityIn);
        this.entity = entityIn;
    }

    @Override
    public boolean shouldExecute() {
        return this.entity.getPassengers().isEmpty() && super.shouldExecute();
    }
}
