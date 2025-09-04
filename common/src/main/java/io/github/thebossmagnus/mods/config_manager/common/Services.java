package io.github.thebossmagnus.mods.config_manager.common;

import io.github.thebossmagnus.mods.config_manager.common.services.IPlatformHelper;

import java.util.ServiceLoader;


public class Services {


    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);


    public static <T> T load(Class<T> clazz) {

        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}