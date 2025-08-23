package io.github.thebossmagnus.mods.config_manager.common_coremod;

import io.github.thebossmagnus.mods.config_manager.common.Services;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;


/**
 * Deletes all config files except the modpacks_defaults directory, then copies configs from modpacks_defaults.
 */
public final class ResetAndCopyConfig {
    private static final String DIR_NAME = "modpacks_defaults";

    /**
     * Deletes all files and folders in config except modpacks_defaults, then copies files from modpacks_defaults.
     */
    public static void run() {
        Path gameDir = Services.PLATFORM.getGameDir();
        Path configDir = gameDir.resolve("config");
        Path defaultsDir = configDir.resolve(DIR_NAME);
        if (!Files.exists(defaultsDir)) {
            return;
        }

        // Delete everything in config except modpacks_defaults
        try (Stream<Path> stream = Files.list(configDir)) {
            stream.filter(path -> !path.getFileName().toString().equals(DIR_NAME))
                  .forEach(ResetAndCopyConfig::deleteRecursively);
        } catch (IOException e) {
            throw new RuntimeException("Failed to clean config directory", e);
        }
        try (Stream<Path> paths = Files.walk(defaultsDir)) {
            paths.filter(path -> !path.equals(defaultsDir)).forEach(source -> {
                Path relative = defaultsDir.relativize(source);
                Path target = configDir.resolve(relative);
                try {
                    if (Files.isDirectory(source)) {
                        Files.createDirectories(target);
                    } else {
                        Files.createDirectories(target.getParent());
                        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error copying " + source + " to " + target, e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to copy modpack_defaults", e);
        }
    }

    /**
     * Recursively deletes a file or directory.
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

