package io.github.thebossmagnus.mods.config_manager.fabric.client;


import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestGui extends Screen {
    public static final Logger LOGGER = LogManager.getLogger();

    public TestGui(Screen screen) {
        super(Component.translatable("Title"));
    }


    @Override
    protected void init() {
        Button buttonWidget = Button.builder(Component.translatable("WOW a button"), (btn) -> {
            LOGGER.info("The button can be pressed, wow^2!");

        }).pos(33,55).build();
        this.addRenderableWidget(buttonWidget);

    }

    @Override
    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(null);
    }

}
