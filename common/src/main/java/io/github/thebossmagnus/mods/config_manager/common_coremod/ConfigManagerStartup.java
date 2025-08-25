package io.github.thebossmagnus.mods.config_manager.common_coremod;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.thebossmagnus.mods.config_manager.common.Constants.LOGGER;

/**
 * Handles config management at startup based on flag files in the config directory.
 */
public final class ConfigManagerStartup {
    public static Logger logger = LoggerFactory.getLogger("config_manager");

    /**
     * Chooses config copy strategy based on flag files in config dir.
     */
    public static void run(Path gameDir) {
        Path configDir = gameDir.resolve("config");
        Path resetFlag = configDir.resolve("CONFIG_MANAGER_RESET_FLAG");
        Path updateFlag = configDir.resolve("CONFIG_MANAGER_UPDATE_FLAG");
        try {
            if (Files.exists(resetFlag)) {
                LOGGER.info("Reset flag detected, running ResetAndCopyConfig");
                ResetAndCopyConfig.run(gameDir, logger);
                Files.deleteIfExists(resetFlag);
                Files.deleteIfExists(updateFlag);
            } else if (Files.exists(updateFlag)) {
                LOGGER.info("Update flag detected, running OverwriteConfig");
                OverwriteConfig.run(gameDir, logger);
                Files.deleteIfExists(updateFlag);
            } else {
                CopyConfig.init(gameDir);
            }
        } catch (Exception e) {
            LOGGER.error("Error during config copy: ", e);
            throw new RuntimeException(e);
        }
    }
}

