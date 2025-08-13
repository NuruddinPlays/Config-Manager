package io.github.thebossmagnus.mods.config_manager.fabric.pre_launch;


import io.github.thebossmagnus.mods.config_manager.common.CopyConfig;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConfigManagerPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        CopyConfig.init();
    }
}
