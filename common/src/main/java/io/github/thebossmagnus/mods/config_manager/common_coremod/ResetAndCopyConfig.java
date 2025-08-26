package io.github.thebossmagnus.mods.config_manager.common_coremod;

import org.apache.logging.log4j.Logger;
import static io.github.thebossmagnus.mods.config_manager.common_coremod.Constants.LOGGER;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


/**
 * Deletes all config files except the modpacks_defaults directory, then copies configs from modpacks_defaults.
 */
public final class ResetAndCopyConfig {
    private static final String DIR_NAME = "modpack_defaults";

    /**
     * Deletes all files and folders in config except modpacks_defaults, then copies files from modpacks_defaults.
     */
    public static void run(Path gameDir, Logger logger) {
        Path configDir = gameDir.resolve("config");

        // Delete everything in config except modpacks_defaults
        try (Stream<Path> stream = Files.list(configDir)) {
            stream.filter(path -> !path.getFileName().toString().equals(DIR_NAME))
                    .forEach(ResetAndCopyConfig::deleteRecursively);
        } catch (IOException e) {
            throw new RuntimeException("Failed to clean config directory", e);
        }
        OverwriteConfig.run(gameDir, logger);
    }

    /**
     * Recursively deletes a file or directory.
     *
     * @param path Path to delete.
     */
    private static void deleteRecursively(Path path) {
        try {
            if (Files.isDirectory(path)) {
                try (Stream<Path> entries = Files.list(path)) {
                    entries.forEach(ResetAndCopyConfig::deleteRecursively);
                }
            }
            Files.deleteIfExists(path);
            LOGGER.info("Deleted config directory {}", path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete " + path, e);
        }
    }
}

