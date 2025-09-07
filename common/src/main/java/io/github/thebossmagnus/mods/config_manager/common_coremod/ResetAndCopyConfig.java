package io.github.thebossmagnus.mods.config_manager.common_coremod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


/**
 * Deletes all config files except the modpack_defaults directory, then copies configs from modpack_defaults.
 */
public final class ResetAndCopyConfig {


    /**
     * Deletes all files and folders in config except modpack_defaults, then copies files from modpacks_defaults.
     */
    public static void run(Path gameDir) {
        Path configDir = gameDir.resolve("config");

        // Delete everything in config except modpacks_defaults
        try (Stream<Path> stream = Files.list(configDir)) {
            stream.filter(path -> !path.getFileName().toString().equals(Constants.DIR_NAME))
                    .forEach(ResetAndCopyConfig::deleteRecursively);
        } catch (IOException e) {
            throw new RuntimeException("Failed to clean config directory", e);
        }
        OverwriteConfig.run(gameDir);
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
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete " + path, e);
        }
    }
}
