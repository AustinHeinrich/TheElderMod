package kobolds.the_elder.entities.ai;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityAIDrownRider extends EntityAIBase {

    private final World world;
    private final EntityCreature creature;

    public EntityAIDrownRider(EntityCreature entityIn, double movementSpeedIn) {
        this.world = entityIn.world;
        this.creature = entityIn;
    }


    @Override
    public boolean shouldExecute() {
        final boolean isRiderInWater = creature.isBeingRidden() && creature.getPassengers().get(0).isInsideOfMaterial(Material.WATER);
        if (isRiderInWater) {

        }



        return false;
    }
}
