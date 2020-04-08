package kobolds.the_elder.entities.render;

import kobolds.the_elder.Elder;
import kobolds.the_elder.entities.EntityKelpie;

import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderKelpie extends RenderLiving<EntityKelpie> {
    private final ResourceLocation TEXTURE = new ResourceLocation(Elder.MODID + ":textures/entity/kelpie.png");
    public static final Factory FACTORY = new Factory();

    public RenderKelpie(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHorse(), 1.0f);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityKelpie entity) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityKelpie> {
        @Override
        public Render<? super EntityKelpie> createRenderFor(RenderManager manager) {
            return new RenderKelpie(manager);
        }
    }
}
