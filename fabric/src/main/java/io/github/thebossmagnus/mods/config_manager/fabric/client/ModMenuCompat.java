package io.github.thebossmagnus.mods.config_manager.fabric.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

@SuppressWarnings("unused")
public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<TestGui> getModConfigScreenFactory() {
        return TestGui::new;
    }
}