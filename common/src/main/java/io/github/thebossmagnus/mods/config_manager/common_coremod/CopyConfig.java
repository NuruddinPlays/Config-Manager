package io.github.thebossmagnus.mods.config_manager.common_coremod;


import io.github.thebossmagnus.mods.config_manager.common.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

public final class CopyConfig {
    public static final String MOD_ID = "config_manager";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init(Path gameDir) {
        LOGGER.info("Loaded, path: {}", gameDir.toString());
    }
}