package dev.kostromdan.mods.crash_assistant.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CrashAssistant {
    public static final String MOD_ID = "crash_assistant";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("EarlyInit");
    }

    public static void loaded() {
        LOGGER.info("Loaded");
    }
}
