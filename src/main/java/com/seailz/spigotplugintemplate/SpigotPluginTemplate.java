package com.seailz.spigotplugintemplate;

import com.seailz.spigotplugintemplate.commands.CommandReport;
import com.seailz.spigotplugintemplate.core.Locale;
import com.seailz.spigotplugintemplate.core.Logger;
import games.negative.framework.BasePlugin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SpigotPluginTemplate extends BasePlugin {

    @Getter
    @Setter
    public static SpigotPluginTemplate instance;
    @Getter
    boolean debug;
    @Getter
    private int minorErrors;
    @Getter
    private int severeErrors;
    @Getter
    private ArrayList<String> debugLog;

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        long start = System.currentTimeMillis();
        setInstance(this);
        Locale.init(this);
        minorErrors = 0;
        severeErrors = 0;
        debugLog = new ArrayList<>();
        this.debug = getConfig().getBoolean("debug");
        saveDefaultConfig();

        long finish = System.currentTimeMillis() - start;
        Logger.log(Logger.LogLevel.SUCCESS, "Started in " + String.valueOf(finish) + "ms!");

        register(RegisterType.COMMAND);
        register(RegisterType.LISTENER);
    }

    public void addError(boolean severe) {
        if (severe) {
            severeErrors++;
        } else {
            minorErrors++;
        }
    }

    public void register(RegisterType type) {
        switch (type) {
            case COMMAND:
                registerCommands(
                        // Insert commands
                        new CommandReport()
                );
                break;
            case LISTENER:
                registerListeners(
                        // Register Listeners
                );
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public enum RegisterType {COMMAND, LISTENER}
}
