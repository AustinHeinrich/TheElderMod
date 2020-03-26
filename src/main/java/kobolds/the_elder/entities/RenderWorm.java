package kobolds.the_elder.entities;

import kobolds.the_elder.Elder;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderWorm extends RenderLiving<EntityWorm> {
    private ResourceLocation mobTexture = new ResourceLocation(Elder.MODID + ":textures/entity/worm.png");
    public static final Factory FACTORY = new Factory();

    public RenderWorm(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSilverfish(), 2.2f);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityWorm entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityWorm> {
        @Override
        public Render<? super EntityWorm> createRenderFor(RenderManager manager) {
            return new RenderWorm(manager);
        }
    }

    protected void preRenderCallback(EntityWorm entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(6.0f, 6.0f, 8.0f);
    }
}
