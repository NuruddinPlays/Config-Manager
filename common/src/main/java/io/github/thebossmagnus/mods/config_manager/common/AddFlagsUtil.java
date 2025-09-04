package io.github.thebossmagnus.mods.config_manager.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddFlagsUtil {
    private static final AtomicBoolean UPDATE_FLAG = new AtomicBoolean(false);
    private static final AtomicBoolean OVERWRITE_FLAG = new AtomicBoolean(false);

    public static boolean isUpdateFlag() {
        return UPDATE_FLAG.get();
    }

    public static void setUpdateFlag(boolean updateFlag) {
        UPDATE_FLAG.set(updateFlag);
        addFlags();
    }

    public static boolean isOverwriteFlag() {
        return OVERWRITE_FLAG.get();
    }

    public static void setOverwriteFlag(boolean overwriteFlag) {
        OVERWRITE_FLAG.set(overwriteFlag);
        addFlags();
    }

    private static void addFlags() throws RuntimeException {
        Path gameDir = Services.PLATFORM.getGameDir();
        Path configDir = gameDir.resolve("config");
        try {
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }
        } catch (IOException e) {
            Constants.LOGGER.error("Could not create config directory: {}", configDir, e);
            throw new RuntimeException("Could not create config directory", e);
        }
        if (OVERWRITE_FLAG.get()) {
            Path flag = configDir.resolve("CONFIG_MANAGER_RESET_FLAG");
            try {
                Files.createFile(flag);
            } catch (IOException e) {
                if (!Files.exists(flag)) {
                    Constants.LOGGER.error("Could not create CONFIG_MANAGER_RESET_FLAG: {}", flag, e);
                    throw new RuntimeException("Could not create CONFIG_MANAGER_RESET_FLAG", e);
                }
                // Ignore if file already exists
            }
        } else if (UPDATE_FLAG.get()) {
            Path flag = configDir.resolve("CONFIG_MANAGER_UPDATE_FLAG");
            try {
                Files.createFile(flag);
            } catch (IOException e) {
                if (!Files.exists(flag)) {
                    Constants.LOGGER.error("Could not create CONFIG_MANAGER_UPDATE_FLAG: {}", flag, e);
                    throw new RuntimeException("Could not create CONFIG_MANAGER_UPDATE_FLAG", e);
                }
                // Ignore if file already exists
            }
        }
    }
}
