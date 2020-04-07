package kobolds.the_elder.entities;

import kobolds.the_elder.Elder;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

// TODO add a player death message for this

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
        PotionEffect potEff = new PotionEffect(MobEffects.SLOWNESS, 100, 1);
        if ((!world.isRemote && result.entityHit != null)  && !(result.entityHit instanceof EntitySlime)) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)dmg);
            ((EntityLivingBase) result.entityHit).addPotionEffect(potEff);
        }
    }
}
