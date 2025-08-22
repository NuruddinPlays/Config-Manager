package io.github.thebossmagnus.mods.config_manager.neoforge;


import io.github.thebossmagnus.mods.config_manager.common.screen.Gui;
import io.github.thebossmagnus.mods.config_manager.common_coremod.CopyConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;


import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = CopyConfig.MOD_ID, dist = Dist.CLIENT)
public class ConfigManagerNeoForgeClient {
    public ConfigManagerNeoForgeClient() {
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (mc, parent) -> new Gui(parent));
    }
}