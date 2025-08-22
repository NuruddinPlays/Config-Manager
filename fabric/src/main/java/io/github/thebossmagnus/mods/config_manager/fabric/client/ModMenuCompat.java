package io.github.thebossmagnus.mods.config_manager.fabric.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.thebossmagnus.mods.config_manager.common.screen.Gui;

@SuppressWarnings("unused")
public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<Gui> getModConfigScreenFactory() {
        return Gui::new;
    }
}