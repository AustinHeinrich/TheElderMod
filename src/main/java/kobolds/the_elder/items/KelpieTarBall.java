package kobolds.the_elder.items;

import kobolds.the_elder.Elder;

import kobolds.the_elder.entities.EntityKelpieTarBall;
import kobolds.the_elder.items.base.ItemThrownBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

// TODO add a texture to the thrown ball

public class KelpieTarBall extends ItemThrownBase {

    public KelpieTarBall(String name) {
        super(name);
        this.setCreativeTab(Elder.ELDER_TAB);
    }

    // what happens when the player right-clicks this item in their inventory
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            EntityKelpieTarBall tarBall = new EntityKelpieTarBall(worldIn, playerIn);
            tarBall.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(tarBall);
        }

        playerIn.getCooldownTracker().setCooldown(this, 15);
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    }
}
