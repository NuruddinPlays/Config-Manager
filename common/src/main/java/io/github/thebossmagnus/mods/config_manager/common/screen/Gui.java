package io.github.thebossmagnus.mods.config_manager.common.screen;


import io.github.thebossmagnus.mods.config_manager.common.AddFlagsUtil;
import io.github.thebossmagnus.mods.config_manager.common.Constants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;


public class Gui extends Screen {
    private static final int buttonWidth = 150;
    private static final int buttonHeight = 20;
    private static final int COLOR_RED = 0xFF0000;
    private static final int COLOR_WHITE = 0xFFFFFF;
    private final Component updateWarnings = Component.translatable("* %s\n* %s",
            Component.translatable("config_manager.warning.lose_some_config"),
            Component.translatable("config_manager.warning.game_restart")
    );
    private final Component resetWarnings = Component.translatable("* %s\n* %s",
            Component.translatable("config_manager.warning.lose_all_config"),
            Component.translatable("config_manager.warning.game_restart")
    );
    private MultilineLabelWidget l1;
    private MultilineLabelWidget l2;
    private boolean updateFirstClick = true;
    private boolean resetFirstClick = true;

    public Gui(Screen screen) {
        super(Component.translatable("config_manager.title"));
    }

    private Button createConfigButton(Component label, Runnable flagSetter, boolean isFirstClick, java.util.function.Consumer<Boolean> firstClickUpdater) {
        return Button.builder(label, (btn) -> {
            if (isFirstClick) {
                btn.setMessage(Component.translatable("config_manager.confirmation").withStyle(style -> style.withColor(COLOR_RED)));
                firstClickUpdater.accept(false);
            } else {
                try {
                    flagSetter.run();
                    btn.setMessage(Component.translatable("config_manager.success").withStyle(style -> style.withColor(COLOR_WHITE)));
                    btn.active = false;
                } catch (Exception e) {
                    Constants.LOGGER.error("Failed to set flag", e);
                    btn.setMessage(Component.translatable("config_manager.error").withStyle(style -> style.withColor(COLOR_RED)));
                }
            }
        })
        .size(buttonWidth, buttonHeight)
        .build();
    }

    @Override
    protected void init() {
        Button updateButton = createConfigButton(
                Component.translatable("config_manager.update_config"),
                () -> AddFlagsUtil.setUpdateFlag(true),
                updateFirstClick,
                (val) -> updateFirstClick = val
        );
        updateButton.setPosition((int) ((this.width - buttonWidth) * 0.15), (int) ((this.height - buttonHeight) * 0.7));

        Button resetButton = createConfigButton(
                Component.translatable("config_manager.reset_config"),
                () -> AddFlagsUtil.setOverwriteFlag(true),
                resetFirstClick,
                (val) -> resetFirstClick = val
        );
        resetButton.setPosition((int) ((this.width - buttonWidth) * 0.7), (int) ((this.height - buttonHeight) * 0.7));

        Button closeButton = Button.builder(Component.translatable("config_manager.close"), (btn) -> {
            this.onClose();
        }).pos((int) ((this.width - buttonWidth) * 0.5), (int) ((this.height - buttonHeight) * 0.95)).size(buttonWidth, buttonHeight).build();

        this.addRenderableWidget(resetButton);
        this.addRenderableWidget(updateButton);
        this.addRenderableWidget(closeButton);

        l1 = new MultilineLabelWidget(
                this.font,
                updateWarnings,
                (int) ((this.width - buttonWidth) * 0.15),
                (int) ((this.height - buttonHeight) * 0.7) - 90,
                buttonWidth,
                true
        );

        l2 = new MultilineLabelWidget(
                this.font,
                resetWarnings,
                (int) ((this.width - buttonWidth) * 0.7),
                (int) ((this.height - buttonHeight) * 0.7) - 90,
                buttonWidth,
                true
        );
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
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
