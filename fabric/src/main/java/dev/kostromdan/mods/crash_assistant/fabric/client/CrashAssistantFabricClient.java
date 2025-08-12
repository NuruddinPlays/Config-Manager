package dev.kostromdan.mods.crash_assistant.fabric.client;


import dev.kostromdan.mods.crash_assistant.common.CrashAssistant;
import net.fabricmc.api.ClientModInitializer;


public final class CrashAssistantFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CrashAssistant.loaded();
    }
}