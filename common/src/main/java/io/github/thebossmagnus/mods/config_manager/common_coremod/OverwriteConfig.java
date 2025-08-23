package io.github.thebossmagnus.mods.config_manager.common_coremod;

import io.github.thebossmagnus.mods.config_manager.common.Services;
import io.github.thebossmagnus.mods.config_manager.common.services.IPlatformHelper;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;
import static io.github.thebossmagnus.mods.config_manager.common.Loaded.LOGGER;

/**
 * Copies config files from modpacks_defaults, overwriting any existing files in the config directory.
 */
public final class OverwriteConfig {
    private static final String DIR_NAME = "modpacks_defaults";


    public static void run() {
        Path gameDir = Services.PLATFORM.getGameDir();
        Path configDir = gameDir.resolve("config");
        Path defaultsDir = configDir.resolve(DIR_NAME);
        if (!Files.exists(defaultsDir)) {
            return;
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
                        // Overwrite existing files
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
}

