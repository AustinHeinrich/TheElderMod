package kobolds.the_elder.entities;

import kobolds.the_elder.Elder;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderTemp extends RenderLiving<EntityTemp> {
    private ResourceLocation mobTexture = new ResourceLocation(Elder.MODID + ":textures/entity/temp.png");
    public static final Factory FACTORY = new Factory();

    public RenderTemp(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSilverfish(), 0.5f);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTemp entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTemp> {
        @Override
        public Render<? super EntityTemp> createRenderFor(RenderManager manager) {
            return new RenderTemp(manager);
        }
    }
}
