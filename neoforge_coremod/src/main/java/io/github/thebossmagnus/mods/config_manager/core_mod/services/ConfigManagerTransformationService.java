package io.github.thebossmagnus.mods.config_manager.core_mod.services;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import io.github.thebossmagnus.mods.config_manager.common.CopyConfig;
import org.jetbrains.annotations.NotNull;


import java.util.List;
import java.util.Set;

/**
 * CrashAssistantApp should be launched as soon as possible after game start
 * to be able to help players even with coremod/mixin/hs_err crashes.
 * So we launch it from initialize of ITransformationService, the first point, we can launch it from forge mod.
 */
public class ConfigManagerTransformationService implements ITransformationService {

    @Override
    public @NotNull String name() {
        return "config_manager";
    }

    @Override
    public void initialize(IEnvironment environment) {
        CopyConfig.init();
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) {

    }

    @Override
    public @NotNull List<? extends ITransformer<?>> transformers() {
        return List.of();
    }
}
