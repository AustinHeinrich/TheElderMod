package kobolds.the_elder.entities;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAITempAttack extends EntityAIAttackMelee {
    private int raiseArmTicks;
    private EntityTemp me;

    public EntityAITempAttack(EntityTemp meIn, double speedIn, boolean longMemoryIn) {
        super(meIn, speedIn, longMemoryIn);
        this.me = meIn;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.me.setArmsRaised(false);
    }

    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.me.setArmsRaised(true);
        }
        else {
            this.me.setArmsRaised(false);
        }
    }
}
