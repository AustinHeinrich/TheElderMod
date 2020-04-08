package kobolds.the_elder.entities.ai;

import kobolds.the_elder.entities.EntityWorm;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIWormAttack extends EntityAIAttackMelee {
    private EntityWorm me;

    public EntityAIWormAttack(EntityWorm meIn, double speedIn, boolean longMemoryIn) {
        super(meIn, speedIn, longMemoryIn);
        this.me = meIn;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void resetTask() {
        super.resetTask();
    }

    @Override
    public void updateTask() {
        super.updateTask();
    }
}
