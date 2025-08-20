package io.github.thebossmagnus.mods.config_manager.fabric.client;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestGui extends Screen {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final int buttonWidth = 100;
    private static final int buttonHeight = 20;

    private MultilineLabelWidget multilineLabel;

    public TestGui(Screen screen) {
        super(Component.translatable("Title"));
    }


    @Override
    protected void init() {
        Button testButton = Button.builder(Component.translatable("WOW a button"), (btn) -> {
            LOGGER.info("The button can be pressed, wow^2!");

        }).pos(0,0).size(100,20).size(buttonWidth, buttonHeight).build();
        Button closeButton = Button.builder(Component.translatable("Close"), (btn) -> {
            this.onClose();

        }).pos((int) ((this.width-buttonWidth)*0.5), (int) ((this.height-buttonHeight)*0.9)).size(buttonWidth, buttonHeight).build();
        this.addRenderableWidget(testButton);
        this.addRenderableWidget(closeButton);

        multilineLabel = new MultilineLabelWidget(
            this.font,
            Component.translatable("testgui.multiline"),
            0,
            33,
            this.width,
            true
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        if (multilineLabel != null) {
            multilineLabel.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }



    @Override
    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(null);
    }

}
