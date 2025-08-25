package io.github.thebossmagnus.mods.config_manager.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AddFlagsUtil {


    private static boolean UPDATE_FLAG = false;
    private static boolean OVERWRITE_FLAG = false;

    public static boolean isUpdateFlag() {
        return UPDATE_FLAG;
    }

    public static void setUpdateFlag(boolean updateFlag) {
        UPDATE_FLAG = updateFlag;
        addFlags();

    }

    public static boolean isOverwriteFlag() {
        return OVERWRITE_FLAG;
    }

    public static void setOverwriteFlag(boolean overwriteFlag) {
        OVERWRITE_FLAG = overwriteFlag;
        addFlags();
    }

    private static void addFlags() throws RuntimeException {
        Path gameDir = Services.PLATFORM.getGameDir();
        if (OVERWRITE_FLAG) {
            Path flag = gameDir.resolve("config").resolve("CONFIG_MANAGER_RESET_FLAG");
            try {
                Files.createFile(flag);
            } catch (IOException e) {
                if (!Files.exists(flag)) {
                    throw new RuntimeException("Could not create CONFIG_MANAGER_RESET_FLAG", e);
                }
                // Ignore if file already exists
            }
        } else if (UPDATE_FLAG) {
            Path flag = gameDir.resolve("config").resolve("CONFIG_MANAGER_UPDATE_FLAG");
            try {
                Files.createFile(flag);
            } catch (IOException e) {
                if (!Files.exists(flag)) {
                    throw new RuntimeException("Could not create CONFIG_MANAGER_UPDATE_FLAG", e);
                }
                // Ignore if file already exists
            }
        }
    }
}
