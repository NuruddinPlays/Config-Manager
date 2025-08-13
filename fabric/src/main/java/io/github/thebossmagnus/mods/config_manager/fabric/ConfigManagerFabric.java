package io.github.thebossmagnus.mods.config_manager.fabric;

import io.github.thebossmagnus.mods.config_manager.common.Loaded;
import net.fabricmc.api.ModInitializer;

public final class ConfigManagerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Loaded.loade();
    }
}
