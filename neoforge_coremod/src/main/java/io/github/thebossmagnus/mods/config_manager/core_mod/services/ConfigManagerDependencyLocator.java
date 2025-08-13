package io.github.thebossmagnus.mods.config_manager.core_mod.services;

import cpw.mods.jarhandling.SecureJar;
import net.neoforged.fml.loading.moddiscovery.locators.JarInJarDependencyLocator;
import net.neoforged.fml.loading.moddiscovery.readers.JarModsDotTomlModFileReader;
import net.neoforged.neoforgespi.locating.IDependencyLocator;
import net.neoforged.neoforgespi.locating.IDiscoveryPipeline;
import net.neoforged.neoforgespi.locating.IModFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;


/**
 * Since neoforge doesn't load jar in jar mods from coremods, we should do it by ourselves.
 */
public class ConfigManagerDependencyLocator extends JarInJarDependencyLocator implements IDependencyLocator {
    public static final Logger LOGGER = LoggerFactory.getLogger("ConfigManagerDependencyLocator");

    @Override
    public void scanMods(List<IModFile> loadedMods, IDiscoveryPipeline pipeline) {
        try {
            Path jarPath = Path.of(ConfigManagerDependencyLocator.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            SecureJar secureJar = SecureJar.from(jarPath);

            IModFile modFile = IModFile.create(secureJar, JarModsDotTomlModFileReader::manifestParser);

            Path innerJarPath = Path.of("META-INF", "jarjar", "config_manager-neoforge.jar");

            Optional<IModFile> neoForgeMod = loadModFileFrom(modFile, innerJarPath, pipeline);

            neoForgeMod.ifPresent(pipeline::addModFile);

        } catch (Exception e) {
            LOGGER.error("Error while adding config_manager-neoforge.jar to pipeline: ", e);
        }
    }
}
