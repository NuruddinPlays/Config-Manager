package io.github.thebossmagnus.mods.config_manager_coremod;

import io.github.thebossmagnus.mods.config_manager.common.CopyConfig;
import net.neoforged.fml.common.Mod;

@Mod(CopyConfig.MOD_ID)
public final class ConfigManagerNeoForge {
    public ConfigManagerNeoForge() {
        CopyConfig.loaded();

    }

}