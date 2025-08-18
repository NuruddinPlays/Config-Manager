package io.github.thebossmagnus.mods.config_manager.fabric.client;


import io.github.thebossmagnus.mods.config_manager.common.Loaded;
import net.fabricmc.api.ClientModInitializer;


public final class ConfigManagerFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Loaded.loade();
    }

}