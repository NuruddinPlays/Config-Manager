package io.github.thebossmagnus.mods.config_manager.common_coremod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CopyConfig {
    public static final String MOD_ID = "config_manager";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("EarlyInit");
    }


}
