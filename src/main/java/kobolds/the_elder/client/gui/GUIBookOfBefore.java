package kobolds.the_elder.client.gui;

import com.jcraft.jogg.Page;
import kobolds.the_elder.Elder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GUIBookOfBefore extends GuiScreen {

    private static final int BOOK_PAGES = 3;
    private static final int BOOK_HEIGHT = 192;
    private static final int BOOK_WIDTH = 192;

    private int currPage = 0;

    private static ResourceLocation[] bookPageTexture;
    private static String[] pageText;
    private GuiButton exitButton;
    private PageTurnButton nextButton;
    private PageTurnButton prevButton;

    public GUIBookOfBefore() {
        bookPageTexture = new ResourceLocation[BOOK_PAGES];
        bookPageTexture[0] = new ResourceLocation(Elder.MODID, "textures/gui/book_of_before_gui.png");
        bookPageTexture[1] = bookPageTexture[0]; // page 2 should be portal configuration, and instruction to use book to open portal
        bookPageTexture[2] = bookPageTexture[0];

        pageText = new String[BOOK_PAGES];
        pageText[0] = "The Elder \n " +
                "Before there was time. \n " +
                "Before there was anything. \n " +
                "There was the world that was and we called it home. \n " +
                "Now we lie forgotten in past beyond past, grown distant yet strong. Search for us there, dreamer.";
        pageText[1] = "To enter the Elder \n" +
                "Construct two blocks: elder portal frames, elder portal cores. Construct the portal frames on flat ground, like an end portal, placing the cores in the corners. Attach cores with frames and right click the cores with this book. The center of the portal";
        pageText[2] = "must be the center of the world. (coordinates x=0, z=0 should be the center of the portal)";
    }

    // adds buttons and controls to the gui
    @Override
    public void initGui() {
        int offsetFromScreenLeft = (width - BOOK_WIDTH) / 2;
        int buttonId = 0;

        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        // exit button
        exitButton = new GuiButton(buttonId++, width / 2, 4 + BOOK_HEIGHT, 98, 20,
                I18n.format("gui.done", new Object[0]));
        buttonList.add(exitButton);

        // arrows
        this.nextButton = (PageTurnButton)this.addButton(new PageTurnButton(buttonId++, offsetFromScreenLeft+120, 156, true));
        this.prevButton = (PageTurnButton)this.addButton(new PageTurnButton(buttonId++, offsetFromScreenLeft+38, 156, false));
        this.updateScreen();
    }

    @Override
    public void updateScreen() {
        prevButton.visible = currPage > 0;
        nextButton.visible = (currPage < BOOK_PAGES - 1);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int offsetFromScreenLeft = (width - BOOK_WIDTH) / 2;
        String pageIndicator = I18n.format("book.pageIndicator",
                new Object[] {Integer.valueOf(currPage + 1), BOOK_PAGES});

        drawDefaultBackground(); // darken the background

        // book
        this.mc.renderEngine.bindTexture(bookPageTexture[currPage]);
        drawTexturedModalRect(offsetFromScreenLeft, 2, 0,0, BOOK_HEIGHT, BOOK_WIDTH);

        // text
        this.fontRenderer.drawSplitString(pageText[currPage],offsetFromScreenLeft + 36, 34, 116,0x00000);
        this.fontRenderer.drawString(pageIndicator, offsetFromScreenLeft -
                this.fontRenderer.getStringWidth(pageIndicator) + BOOK_WIDTH - 44, 18, 0 );

        super.drawScreen(mouseX, mouseY, partialTicks);
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

    @SideOnly(Side.CLIENT)
    static class PageTurnButton extends GuiButton {
        private boolean isPageForwardButton;
        int positionX;
        int positionY;

        public PageTurnButton(int buttonId, int posX, int posY, boolean isPageForwardButton) {
            super(buttonId, posX, posY, 23, 15,"");
            this.isPageForwardButton = isPageForwardButton;
            this.positionX = posX;
            this.positionY = posY;
        }

        // draw the button on screen
        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (visible) {
                int textureX = 0;
                int textureY = 192;
                boolean isButtonHover =
                        (mouseX >= this.positionX &&
                         mouseY >= this.positionY &&
                         mouseX < this.positionX + width &&
                         mouseY < this.positionY + height);

                if (!isPageForwardButton) {
                    textureY += 13;
                }

                if (isButtonHover) {
                    textureX += 23;
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(bookPageTexture[0]);
                drawTexturedModalRect(this.positionX, this.positionY, textureX, textureY, 25, 13);
            }
        }
    }
}
