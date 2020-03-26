// Author: Lachlan Muir

package kobolds.the_elder.blocks.base;

import kobolds.the_elder.Elder;
import kobolds.the_elder.enums.WoodVariant;
import kobolds.the_elder.init.ModBlocks;
import kobolds.the_elder.init.ModItems;
import kobolds.the_elder.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WoodBlockBase extends BlockLog implements IHasModel {

    public static final IProperty<WoodVariant> VARIANT = PropertyEnum.create("variant", WoodVariant.class);

    /*
    A new instance of this class should be registered for each type of log in the game
     */
    public WoodBlockBase(String name) {
        // properties
        setHardness(3.0F);
        setSoundType(SoundType.WOOD);
        setDefaultState(this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.Y));

        // registration
        setRegistryName(name);
        setUnlocalizedName(Elder.MODID + "." + name);
        setCreativeTab(Elder.ELDER_TAB);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState blockState = this.getDefaultState().withProperty(VARIANT, WoodVariant.values()[meta & 3]);

        // use meta & 0b1100 to ensure that the case value is 0, 4, 8, or 12. default is used to represent the 12 case.
        // example: meta == 1 (binary: 0b0001). meta & 0b1100 == 0b0000
        // example: meta == 11 (binary: 0b1011). meta & 0b1100 == 0b1000
        switch (meta & 0b1100) {
            case 0:
                blockState = blockState.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                blockState = blockState.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                blockState = blockState.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                blockState = blockState.withProperty(LOG_AXIS, EnumAxis.NONE);
        }

        return blockState;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        // get the ordering in the enum for the given state
        int meta = state.getValue(VARIANT).ordinal();

        switch (state.getValue(LOG_AXIS)) {
            case Y:
                meta |= 0;
                break;
            case X:
                meta |= 4;
                break;
            case Z:
                meta |= 8;
                break;
            case NONE:
                meta |= 12;

        }

        return meta;
    }

    // TODO: may need to add an Override for canSilkHarvest


    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
        items.add(new ItemStack(this, 1, 3));
    }

    // TODO: may want to create a registerModel() function - reference TForest
    public void registerModels() {
        Elder.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }


}
