package io.github.thebossmagnus.mods.config_manager.fabric.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Widget for rendering multi-line text with formatting in Minecraft GUIs.
 */
public class MultilineLabelWidget implements Renderable {
    private final Font font;
    private final Component text;
    private final int x;
    private final int y;
    private final int width;
    private final boolean centered;

    /**
     * Constructs a MultilineLabelWidget.
     *
     * @param font Font renderer used for drawing text
     * @param text The text component to display (can contain formatting and siblings)
     * @param x X position of the widget
     * @param y Y position of the widget
     * @param width Width of the widget (used for centering)
     * @param centered Whether to center the text horizontally
     */
    public MultilineLabelWidget(Font font, Component text, int x, int y, int width, boolean centered) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.centered = centered;
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        // Make a list with text and it's siblings
        List<Component> allComponents = new ArrayList<>();
        allComponents.add(text);
        allComponents.addAll(text.getSiblings());

        List<MutableComponent> OutputLines = new ArrayList<>();

        // For each component, split by '\n' and preserve style
        for (Component comp : allComponents) {
            String textContent = comp.getString();
            String[] split = textContent.split("\\n", -1);
            for (String part : split) {
                if (!part.isEmpty()) {
                    // Each line preserves the style of its originating component
                    MutableComponent line = Component.literal(part).withStyle(comp.getStyle());
                    OutputLines.add(line);
                }
            }
        }

        int currentY = y;
        for (MutableComponent lineComponent : OutputLines) {
            int drawX = centered ? x + width / 2 : x;
            if (centered) {
                guiGraphics.drawCenteredString(font, lineComponent, drawX, currentY, 0xFFFFFFFF);
            } else {
                guiGraphics.drawString(font, lineComponent, drawX, currentY, 0xFFFFFFFF);
            }
            currentY += font.lineHeight + 5;
        }
    }
}
