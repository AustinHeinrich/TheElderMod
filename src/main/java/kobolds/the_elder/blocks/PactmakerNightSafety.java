package kobolds.the_elder.blocks;

import kobolds.the_elder.Elder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/*
 * Most common pactmaker, found in the Night Safety Station structure
 */

public class PactmakerNightSafety extends Block {

    public PactmakerNightSafety() {
        super(Material.REDSTONE_LIGHT);
        float hardness = 50f;
        float lightLevel = 0.5f;

        this.setHardness(hardness);
        this.setLightLevel(lightLevel);
        this.setCreativeTab(Elder.ELDER_TAB);
    }

    // when the user right-clicks the pactmaker, do stuff
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int minExperienceLevel = 20;

        if (worldIn.isRemote) {
            ITextComponent vitalityTraderMsg = null;
            int playerXP = playerIn.experienceLevel;

            if (playerXP >= minExperienceLevel && playerIn.isPotionActive(MobEffects.WITHER)) {
                playerIn.experienceLevel = playerXP - minExperienceLevel;
                vitalityTraderMsg = new TextComponentString("Walk safely in the night.");


            } else {
                vitalityTraderMsg = new TextComponentString("The night harms those without the experience to deal.");
            }

            playerIn.sendMessage(vitalityTraderMsg);
            return true;
        }

        return false;
    }
}
