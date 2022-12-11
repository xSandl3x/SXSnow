package ua.xsandl3x.sxsnow;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ua.xsandl3x.sxsnow.commands.impl.SnowCommand;
import ua.xsandl3x.sxsnow.commands.manager.CommandManager;
import ua.xsandl3x.sxsnow.configuration.impl.SnowConfig;
import ua.xsandl3x.sxsnow.listeners.*;
import ua.xsandl3x.sxsnow.snow.manager.SnowManager;
import ua.xsandl3x.sxsnow.storage.impl.SQLManager;
import ua.xsandl3x.sxsnow.updater.UpdateCheck;
import ua.xsandl3x.sxsnow.utils.TemplateUtil;

@Getter
public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    private SnowConfig snowConfig;
    private SQLManager sqlManager;
    private UpdateCheck updateCheck;
    private SnowManager snowManager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.snowConfig = new SnowConfig(this);
        this.snowConfig.load();

        this.updateCheck = new UpdateCheck(this, 106561);
        this.updateCheck.search();

        this.snowManager = new SnowManager(this);
        this.snowManager.load();

        this.sqlManager = new SQLManager(this);
        this.sqlManager.load();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new UpdateListener(), this);
        pluginManager.registerEvents(new WeatherListener(), this);
        pluginManager.registerEvents(new SnowListener(), this);

        CommandManager.INSTANCE.register(new SnowCommand());

        TemplateUtil.pluginStartMessage();
    }

    @Override
    public void onDisable() {
        CommandManager.INSTANCE.unregister(new SnowCommand());

        this.sqlManager.unload();
        this.snowManager.unload();

        HandlerList.unregisterAll(this);
        TemplateUtil.pluginStopMessage();
    }
}
