package kobolds.the_elder.entities;

import kobolds.the_elder.Elder;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderKelpieTarBall extends Render<EntityKelpieTarBall> {
    private ResourceLocation projectileTexture;
    public static final Factory FACTORY = new Factory();

    protected RenderKelpieTarBall(RenderManager renderManager) {
        super(renderManager);
        this.projectileTexture = new ResourceLocation(Elder.MODID, "textures/items/kelpie_tar_ball.png");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityKelpieTarBall entity) {
        return this.projectileTexture;
    }

    public static class Factory implements IRenderFactory<EntityKelpieTarBall> {
        public Render<? super EntityKelpieTarBall> createRenderFor(RenderManager manager) {
            return new RenderKelpieTarBall(manager);
        }
    }
}