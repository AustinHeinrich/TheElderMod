package kobolds.the_elder.client.gui;

import kobolds.the_elder.Elder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderTooltipEvent;

import java.io.IOException;

public class GUIBookOfBefore extends GuiScreen {

    private static final int BOOK_PAGES = 2;
    private int currPage = 0;
    private static final int GUI_HEIGHT = 192;
    private static final int GUI_WIDTH = 192;

    private static ResourceLocation[] bookPageTexture = new ResourceLocation[BOOK_PAGES];
    private static String[] pageText = new String[BOOK_PAGES];

    public GUIBookOfBefore() {
        bookPageTexture[0] = new ResourceLocation(Elder.MODID, "textures/gui/book_of_before.png");
        bookPageTexture[1] = bookPageTexture[0];

        pageText[0] = "The Elder \n test test test test";
        pageText[1] = "page 2 test test test test test test";
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerY = (height / 2) - GUI_HEIGHT / 2;
        int centerX = (width / 2) - GUI_WIDTH / 2;

        drawDefaultBackground(); // darken the background

        // book
        this.mc.renderEngine.bindTexture(bookPageTexture[currPage]);
        drawTexturedModalRect(centerX, centerY, 0,0, GUI_HEIGHT, GUI_WIDTH);

        // text
        this.fontRenderer.drawSplitString(pageText[currPage], centerX+36, 36, 116,0x00000);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    // gui will not pause game in single player
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
