package io.github.thebossmagnus.mods.config_manager.neoforge;

import io.github.thebossmagnus.mods.config_manager.common.Constants;
import io.github.thebossmagnus.mods.config_manager.common.Loaded;


import net.neoforged.fml.common.Mod;


@Mod(Constants.MOD_ID)
public final class ConfigManagerNeoForge {
    public ConfigManagerNeoForge() {

        Loaded.loade();
}
}