package dev.kostromdan.mods.crash_assistant.neoforge;

import com.mojang.brigadier.CommandDispatcher;
import dev.kostromdan.mods.crash_assistant.common.CrashAssistant;

import net.neoforged.fml.common.Mod;

@Mod(CrashAssistant.MOD_ID)
public final class CrashAssistantNeoForge {
    public CrashAssistantNeoForge() {
        CrashAssistant.loaded();

    }

}