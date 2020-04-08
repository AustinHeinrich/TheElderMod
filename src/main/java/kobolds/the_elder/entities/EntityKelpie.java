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

// forced mount done as an event
// much credit to Darkhax

public class EntityKelpie extends EntityHorse {

    public static ResourceLocation LOOT = new ResourceLocation(Elder.MODID, "entities/kelpie");

    public EntityKelpie(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimmingConsiderPassenger(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));

        // TODO grab a player and force them as passenger

        // TODO if it has a passenger, swim until submerged (i.e. at least 3 blocks underwater if available)
        this.tasks.addTask(3, new EntityAIDrownRider(this, 1.2D));

    }

    @Override
    public boolean attackEntityFrom (DamageSource source, float damageAmount) {
        final Entity rider = source.getTrueSource();
        boolean isRidden = this.isRidingOrBeingRiddenBy(rider);

        return rider != null && isRidden ? super.attackEntityFrom(DamageSource.OUT_OF_WORLD, damageAmount / 2F) : super.attackEntityFrom(source, damageAmount);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        this.mountTo(player);
        return false;
    }

    @Override
    public void dismountRidingEntity() {
        // if the Kelpie dies, dismount
        Entity entity = this.getRidingEntity();
        if (entity != null && entity.isDead) {
            super.dismountRidingEntity();
        }
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
        // otherwise forces dismount in water
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
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Override
    public boolean wearsArmor() {
        return false;
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
