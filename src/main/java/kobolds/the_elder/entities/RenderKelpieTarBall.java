package kobolds.the_elder.entities;
import kobolds.the_elder.Elder;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderKelpieTarBall extends Render<EntityKelpieTarBall> {
    private ResourceLocation projectileTexture = new ResourceLocation(Elder.MODID, "textures/items/kelpie_tar_ball.png");
    public static final Factory FACTORY = new Factory();

    protected RenderKelpieTarBall(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityKelpieTarBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityKelpieTarBall entity) {
        return projectileTexture;
    }

    public static class Factory implements IRenderFactory<EntityKelpieTarBall> {
        public Render<? super EntityKelpieTarBall> createRenderFor(RenderManager manager) {
            return new RenderKelpieTarBall(manager);
        }
    }
}