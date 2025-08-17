package io.github.thebossmagnus.mods.config_manager.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loaded {
    public static final String MOD_ID = "config_manager";
    public static final Logger LOGGER = LogManager.getLogger();


    public static void loade() {
        LOGGER.info("Loaded: {}", Services.PLATFORM.getGameDir());
    }
}
