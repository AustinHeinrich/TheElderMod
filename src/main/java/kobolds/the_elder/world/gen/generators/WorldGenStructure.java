// Austin Heinrich
// awh055
// 11177796

package kobolds.the_elder.world.gen.generators;

import kobolds.the_elder.Elder;
import kobolds.the_elder.util.interfaces.IStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenStructure extends WorldGenerator implements IStructure {

    public String structureName;

    // name -- the name of the structure
    public WorldGenStructure(String name) {
        this.structureName = name;
    }

    // worldIn -- world structure is in
    // rand -- a random variable
    // position -- location where structure is spawned
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.generateStructure(worldIn, position);
        return true;
    }

    // world -- world structure is in
    // position -- location where structure is spawned
    public void generateStructure(World world, BlockPos position) {
        MinecraftServer mcServer = world.getMinecraftServer();
        TemplateManager manager = worldServer.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(Elder.MODID, structureName);
        Template template = manager.get(mcServer, location);

        if (template != null) {
            IBlockState state = world.getBlockState(position);
            world.notifyBlockUpdate(position, state, state, 3);
            template.addBlocksToWorldChunk(world, position, settings);
        }
    }
}
