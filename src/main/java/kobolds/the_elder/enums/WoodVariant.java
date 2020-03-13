package kobolds.the_elder.enums;

import net.minecraft.block.BlockPlanks;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum WoodVariant implements IStringSerializable {

    ELDER(BlockPlanks.EnumType.JUNGLE);

    private final BlockPlanks.EnumType plankType;

    WoodVariant(BlockPlanks.EnumType plankType) {
        this.plankType = plankType;
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
