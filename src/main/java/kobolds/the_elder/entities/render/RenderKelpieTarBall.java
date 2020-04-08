package kobolds.the_elder.entities.render;
import kobolds.the_elder.Elder;

import kobolds.the_elder.entities.EntityKelpieTarBall;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class RenderKelpieTarBall extends Render<EntityKelpieTarBall> {
    private final ResourceLocation TEXTURE = new ResourceLocation(Elder.MODID, "textures/items/kelpie_tar_ball.png");
    public static final Factory FACTORY = new Factory();

    protected RenderKelpieTarBall(RenderManager renderManager) {
        super(renderManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityKelpieTarBall entity) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityKelpieTarBall> {
        public Render<? super EntityKelpieTarBall> createRenderFor(RenderManager manager) {
            return new RenderKelpieTarBall(manager);
        }
    }
}