package kobolds.the_elder.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

// TODO add a player death message for this
// TODO this has no texture

// the tar ball that appears after a player right-clicks on a Kelpie Tar Ball item
public class EntityKelpieTarBall extends EntityThrowable {

    public EntityKelpieTarBall(World worldIn) {
        super(worldIn);
    }

    public EntityKelpieTarBall(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    // when smack
    @Override
    protected void onImpact(RayTraceResult result) {
        int dmg = 1;
        PotionEffect potEff = new PotionEffect(MobEffects.SLOWNESS, 20, 1);

        if ((result.entityHit != null)  && !(result.entityHit instanceof EntitySlime)) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)dmg);
            ((EntityLiving) result.entityHit).addPotionEffect(potEff);
        }
    }
}
