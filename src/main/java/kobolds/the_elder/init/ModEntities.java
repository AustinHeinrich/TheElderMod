package kobolds.the_elder.init;

import kobolds.the_elder.Elder;
import kobolds.the_elder.entities.EntityWorm;
import kobolds.the_elder.entities.RenderWorm;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    protected static final ResourceLocation REGENT_WORM = new ResourceLocation(Elder.MODID, "worm");

    public static void init() {
        int id = 1;
        EntityRegistry.registerModEntity(REGENT_WORM, EntityWorm.class, "Worm", id++, Elder.instance, 64, 3, true, 0x996600, 0x00ff00);
        EntityRegistry.addSpawn(EntityWorm.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS, Biomes.ICE_PLAINS);
        LootTableList.register(EntityWorm.LOOT);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityWorm.class, RenderWorm.FACTORY);
    }
}
