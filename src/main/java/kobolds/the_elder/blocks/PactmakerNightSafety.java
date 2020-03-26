package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import kobolds.the_elder.blocks.base.BlockBase;
import kobolds.the_elder.init.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Random;

/*
 * Most common pactmaker, found in the Night Safety Station structure
 */

public class PactmakerNightSafety extends BlockBase {

    public PactmakerNightSafety(String name, Material material) {
        super(name, material);
        float hardness = 50f;
        float lightLevel = 0.5f;

        this.setHardness(hardness);
        this.setLightLevel(lightLevel);
        this.setCreativeTab(Elder.ELDER_TAB);
    }

    // no item
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    // when the user right-clicks the pactmaker, do stuff
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int minExperienceLevel = 10;
        int cycleTicks = 8400;

        PotionEffect immunityPotion = null;
        ITextComponent nightSafetyMsg = null;
        int playerXP = playerIn.experienceLevel;

        if (playerXP >= minExperienceLevel) {
            playerIn.experienceLevel = playerXP - minExperienceLevel;
            nightSafetyMsg = new TextComponentString("Walk safely in the night.");
            immunityPotion = new PotionEffect(ModPotions.ELDER_NIGHT_IMMUNITY, cycleTicks, 2);

            playerIn.removePotionEffect(MobEffects.WITHER);
            playerIn.addPotionEffect(immunityPotion);
            // make them immune to wither effect for 8400 ticks (see events.NightSafetyEvent)
        } else {
            nightSafetyMsg = new TextComponentString("The night harms those without the experience to deal.");
        }

        if (worldIn.isRemote) {
            playerIn.sendMessage(nightSafetyMsg);
        }
        return true;
    }
}
