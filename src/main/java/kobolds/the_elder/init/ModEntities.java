package kobolds.the_elder.init;

import kobolds.the_elder.Elder;
import kobolds.the_elder.entities.EntityKelpie;
import kobolds.the_elder.entities.EntityKelpieTarBall;
import kobolds.the_elder.entities.EntityWorm;
import kobolds.the_elder.entities.render.RenderKelpie;
import kobolds.the_elder.entities.render.RenderWorm;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    protected static final ResourceLocation KELPIE = new ResourceLocation(Elder.MODID, "kelpie");
    protected static final ResourceLocation KELPIE_TAR_BALL = new ResourceLocation(Elder.MODID, "kelpie_tar_ball");
    protected static final ResourceLocation REGENT_WORM = new ResourceLocation(Elder.MODID, "worm");

    public static void init() {
        int id = 1;
        EntityRegistry.registerModEntity(REGENT_WORM, EntityWorm.class, "Worm", id++, Elder.instance,
                64, 3, true, 0x996600, 0x00ff00);
        EntityRegistry.addSpawn(EntityWorm.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);
        LootTableList.register(EntityWorm.LOOT);

        EntityRegistry.registerModEntity(KELPIE, EntityKelpie.class, "Kelpie", id++, Elder.instance,
                25, 3, true, 0x301934, 0xfffff);
        EntityRegistry.addSpawn(EntityKelpie.class, 100, 3, 5, EnumCreatureType.WATER_CREATURE);
        LootTableList.register(EntityKelpie.LOOT);

        EntityRegistry.registerModEntity(KELPIE_TAR_BALL, EntityKelpieTarBall.class, "Kelpie Tar Ball",
                id++, Elder.instance, 64, 10, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityWorm.class, RenderWorm.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityKelpie.class, RenderKelpie.FACTORY);
        // RenderingRegistry.registerEntityRenderingHandler(EntityKelpieTarBall.class, RenderKelpieTarBall.FACTORY);
    }
}