/**
 * Adapted from https://github.com/KostromDan/Crash-Assistant, LGPL-3.0
 */


package io.github.thebossmagnus.mods.config_manager.core_mod.services;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import io.github.thebossmagnus.mods.config_manager.common_coremod.CopyConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * The mod should be launched as soon as possible after game start
 * to be able to help players even with coremod/mixin/hs_err crashes.
 * So we launch it from initialize of ITransformationService, the first point, we can launch it from forge mod.
 */
public class ConfigManagerTransformationService implements ITransformationService {

    public ConfigManagerTransformationService() {
        CopyConfig.init();
    }

    @Override
    public @NotNull String name() {
        return "config_manager";
    }

    @Override
    public void initialize(IEnvironment environment) {
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) {
    }

    @Override
    public @NotNull List<? extends ITransformer<?>> transformers() {
        return List.of();
    }
}
