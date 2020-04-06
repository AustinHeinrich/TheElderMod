package kobolds.the_elder.client.gui;

import kobolds.the_elder.Elder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GUIBookOfBefore extends GuiScreen {

    private static final int BOOK_PAGES = 2;
    private static final int BOOK_HEIGHT = 192;
    private static final int BOOK_WIDTH = 192;

    private int currPage = 0;

    private static ResourceLocation[] bookPageTexture = new ResourceLocation[BOOK_PAGES];
    private static String[] pageText = new String[BOOK_PAGES];
    private GuiButton exitButton;
    private GuiButton nextButton;
    private GuiButton prevButton;

    public GUIBookOfBefore() {
        bookPageTexture[0] = new ResourceLocation(Elder.MODID, "textures/gui/book_of_before.png");
        bookPageTexture[1] = bookPageTexture[0];

        pageText[0] = "The Elder \n test test test test";
        pageText[1] = "page 2 test test test test test test";
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int offsetFromScreenLeft = (width - BOOK_WIDTH) / 2;

        drawDefaultBackground(); // darken the background

        // book
        this.mc.renderEngine.bindTexture(bookPageTexture[currPage]);
        drawTexturedModalRect(offsetFromScreenLeft, 2, 0,0, BOOK_HEIGHT, BOOK_WIDTH);

        // text
        this.fontRenderer.drawSplitString(pageText[currPage],offsetFromScreenLeft + 36, 34, 116,0x00000);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        int offsetFromScreenLeft = (width - BOOK_WIDTH) / 2;

        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        exitButton = new GuiButton(0, width / 2, 4 + BOOK_HEIGHT, 98, 20,
                I18n.format("gui.done", new Object[0]));
        buttonList.add(exitButton);
    }

    // work the buttons
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == exitButton) {
            mc.displayGuiScreen((GuiScreen) null);
        }
        else if (button == nextButton) {
            if (currPage < BOOK_PAGES - 1) {
                ++currPage;
            }
        }
        else if (button == prevButton) {
            if (currPage > 0) {
                --currPage;
            }
        }
    }

    // gui will not pause game in single player
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
