package kobolds.the_elder.entities.ai;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
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
            // make you weak
            for (Entity passenger : this.creature.getPassengers()) {
                if (passenger instanceof EntityLiving) {
                    ((EntityLiving) passenger).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, 0));
                }
            }
            --creature.posY;
        }


        return false;
    }
}
