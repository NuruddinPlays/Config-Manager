package io.github.thebossmagnus.mods.config_manager.fabric;

import io.github.thebossmagnus.mods.config_manager.common.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FabricPlatformHelper implements IPlatformHelper {


    @Override
    public Path getGameDir() {
        return FabricLoader.getInstance().getGameDir();
    }
}
