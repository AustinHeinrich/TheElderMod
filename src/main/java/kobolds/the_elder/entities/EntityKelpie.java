package kobolds.the_elder.entities;

import kobolds.the_elder.Elder;

import kobolds.the_elder.entities.ai.EntityAIDrownRider;
import kobolds.the_elder.entities.ai.EntityAISwimmingConsiderPassenger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
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
        this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
        // TODO grab a player and put them as passenger
        // TODO if it has a passenger, swim until submerged (i.e. at least 3 blocks underwater if available)
    }

    @Override
    public boolean attackEntityFrom (DamageSource source, float amount) {
        final Entity entity = source.getTrueSource();
        return entity != null && this.isRidingOrBeingRiddenBy(entity) ? super.attackEntityFrom(DamageSource.OUT_OF_WORLD, amount / 2F) : super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        this.mountTo(player);
        return true;
    }

    @Override
    public void dismountRidingEntity() {
        Entity entity = this.getRidingEntity();
        if (entity != null && entity.isDead) {
            super.dismountRidingEntity();
        }
    }

    @Override
    public boolean canRiderInteract() {
        // THE KELPIE IS IN CONTROL
        return false;
    }

    @Override
    public boolean canPassengerSteer() {
        return false;
    }

    @Override
    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
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
