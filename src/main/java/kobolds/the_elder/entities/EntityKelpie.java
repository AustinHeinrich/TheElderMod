package kobolds.the_elder.entities;

import kobolds.the_elder.Elder;

import kobolds.the_elder.entities.ai.EntityAISwimmingConsiderPassenger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityKelpie extends EntityHorse {

    public static ResourceLocation LOOT = new ResourceLocation(Elder.MODID, "entities/kelpie");

    public EntityKelpie(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimmingConsiderPassenger(this));

        // TODO grab a player and put them as passenger

        // TODO if it has a passenger, swim deeper
    }

    @Override
    public boolean attackEntityFrom (DamageSource source, float damageAmount) {
        final Entity player = source.getTrueSource();
        boolean isRidden = this.isRidingOrBeingRiddenBy(player);

        // allow player to damage the Kelpie while it is mounted, damages Kelpie out of water
        return (player != null) && isRidden ? super.attackEntityFrom(DamageSource.OUT_OF_WORLD, damageAmount*2) : super.attackEntityFrom(source, damageAmount);
    }

    @Override
    public void dismountRidingEntity() {
        super.dismountRidingEntity();
    }

    @Override
    public boolean canPassengerSteer() {
        // THE KELPIE IS IN CONTROL
        return false;
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected boolean canTriggerWalking() {
        // can't break crops by walking
        return false;
    }

    @Override
    protected boolean canMate() {
        // prevents spawning horse babies and survival baby spawning. Can spawn babies in creative
        return false;
    }

    @Override
    public boolean wearsArmor() {
        return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }
}
