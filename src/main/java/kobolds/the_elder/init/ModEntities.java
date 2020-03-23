package kobolds.the_elder.init;

import kobolds.the_elder.Elder;
import kobolds.the_elder.entities.EntityKelpieTarBall;
import kobolds.the_elder.entities.EntityTemp;
import kobolds.the_elder.entities.RenderTemp;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    protected static final ResourceLocation REGENT_TEMP = new ResourceLocation(Elder.MODID, "temp");
    protected static final ResourceLocation KELPIE_TAR_BALL = new ResourceLocation(Elder.MODID, "kelpie_tar_ball");

    public static void init() {
        int id = 1;
        EntityRegistry.registerModEntity(REGENT_TEMP, EntityTemp.class, "Temp", id++, Elder.instance, 64, 3, true, 0x996600, 0x00ff00);
        EntityRegistry.addSpawn(EntityTemp.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);
        LootTableList.register(EntityTemp.LOOT);

        EntityRegistry.registerModEntity(KELPIE_TAR_BALL, EntityKelpieTarBall.class, "Kelpie Tar Ball",
                id++, Elder.instance, 64, 1, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityTemp.class, RenderTemp.FACTORY);
    }
}
