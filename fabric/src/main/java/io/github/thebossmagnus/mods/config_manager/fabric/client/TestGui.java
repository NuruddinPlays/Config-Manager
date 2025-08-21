package io.github.thebossmagnus.mods.config_manager.fabric.client;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestGui extends Screen {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final int buttonWidth = 150;
    private static final int buttonHeight = 20;

    private MultilineLabelWidget l1;
    private MultilineLabelWidget l2;



    public TestGui(Screen screen) {
        super(Component.translatable("config_manager.title"));
    }

    private final Component updateWarnings = Component.translatable("* %s\n* %s",
        Component.translatable("config_manager.warning.lose_some_config"),
        Component.translatable("config_manager.warning.game_restart")
    );

    private final Component resetWarnings = Component.translatable("* %s\n* %s",
            Component.translatable("config_manager.warning.lose_all_config"),
            Component.translatable("config_manager.warning.game_restart")
    );

    @Override
    protected void init() {
        Button b1 = Button.builder(Component.translatable("config_manager.update_config"), (btn) -> {
            LOGGER.info("The button can be pressed, wow^2!");

        }).pos((int) ((this.width - buttonWidth) * 0.15), (int) ((this.height - buttonHeight) * 0.7)).size(buttonWidth,buttonHeight).size(buttonWidth, buttonHeight).build();

        Button b2 = Button.builder(Component.translatable("config_manager.reset_config"), (btn) -> {
            LOGGER.info("The button can be pressed, wow^2!");

        }).pos((int) ((this.width - buttonWidth) * 0.9), (int) ((this.height - buttonHeight) * 0.7)).size(buttonWidth,buttonHeight).size(buttonWidth, buttonHeight).build();

        Button closeButton = Button.builder(Component.translatable("config_manager.close"), (btn) -> {
            this.onClose();

        }).pos((int) ((this.width - buttonWidth) * 0.5), (int) ((this.height - buttonHeight) * 0.95)).size(buttonWidth, buttonHeight).build();

        this.addRenderableWidget(b2);
        this.addRenderableWidget(b1);
        this.addRenderableWidget(closeButton);

        l1 = new MultilineLabelWidget(
                this.font,
                updateWarnings,
                (int) ((this.width - buttonWidth) * 0.15),
                (int) ((this.height - buttonHeight) * 0.7)-90,
                buttonWidth,
                true
        );

        l2 = new MultilineLabelWidget(
                this.font,
                resetWarnings,
                (int) ((this.width - buttonWidth) * 0.9),
                (int) ((this.height - buttonHeight) * 0.7)-90,
                buttonWidth,
                true
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        if (l1 != null && l2 != null) {
            l1.render(guiGraphics, mouseX, mouseY, partialTick);
            l2.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }


    @Override
    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(null);
    }

}
