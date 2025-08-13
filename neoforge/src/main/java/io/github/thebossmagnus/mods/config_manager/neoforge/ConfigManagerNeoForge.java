package io.github.thebossmagnus.mods.config_manager.neoforge;

import io.github.thebossmagnus.mods.config_manager.common.Loaded;
import io.github.thebossmagnus.mods.config_manager.common_coremod.CopyConfig;
import net.neoforged.fml.common.Mod;

@Mod(CopyConfig.MOD_ID)
public final class ConfigManagerNeoForge {
    public ConfigManagerNeoForge() {
        Loaded.loade();

    }

}