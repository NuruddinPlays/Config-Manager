package io.github.thebossmagnus.mods.config_manager.fabric.client;


import com.mojang.authlib.minecraft.client.MinecraftClient;
import io.github.thebossmagnus.mods.config_manager.common.Loaded;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.Screen;


public final class ConfigManagerFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Loaded.loade();
    }

}