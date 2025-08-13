package io.github.thebossmagnus.mods.config_manager.fabric.pre_launch;


import io.github.thebossmagnus.mods.config_manager.common_coremod.CopyConfig;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;


public class ConfigManagerPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        CopyConfig.init();
    }
}
