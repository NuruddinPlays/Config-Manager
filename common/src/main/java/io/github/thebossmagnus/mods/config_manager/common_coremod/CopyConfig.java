package io.github.thebossmagnus.mods.config_manager.common_coremod;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public final class CopyConfig {

    private static final String dirName = "modpacks_defaults";

    public static void init(Path gameDir) {


        Path configDir = gameDir.resolve("config").resolve(dirName);
        if (!Files.exists(configDir)) {
            return;
        }

        // Avoid overriding the modpack configs
        Path nestedDir = configDir.resolve(dirName);
        if (Files.exists(nestedDir)) {
            throw new RuntimeException(String.format("A subfolder called \"%s\" is inside config/%s: %s", dirName, dirName, nestedDir));
        }


        // Copy contents of root/config/dirName into root
        try (var paths = Files.walk(configDir)) {
            paths.filter(path -> !path.equals(configDir))
                    .forEach(source -> {
                        Path relative = configDir.relativize(source);
                        Path target = gameDir.resolve(relative);

                        if (Files.exists(target)) {
                            return;
                        }

                        try {
                            if (Files.isDirectory(source)) {
                                Files.createDirectories(target);
                            } else {
                                Files.createDirectories(target.getParent());
                                Files.copy(source, target);
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