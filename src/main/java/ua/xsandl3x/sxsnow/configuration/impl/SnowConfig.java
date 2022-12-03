package ua.xsandl3x.sxsnow.configuration.impl;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.configuration.factory.AbstractConfiguration;

import java.util.List;

@Getter
public class SnowConfig extends AbstractConfiguration {

    private String prefix;
    private String update;
    private double radius;
    private int amount;
    private long interval;
    private boolean checkUpdate;
    private boolean enable;
    private boolean disableRain;

    private List<String> enabledWorlds;

    public SnowConfig(Main instance) {
        super(instance, "config");
    }

    @Override
    protected void configure(FileConfiguration configuration) {
        this.prefix = configuration.getString(SnowKeys.PREFIX.getKey());
        this.update = configuration.getString(SnowKeys.UPDATE_FOUND.getKey());

        this.radius = configuration.getDouble(SnowKeys.RADIUS.getKey());
        this.amount = configuration.getInt(SnowKeys.AMOUNT.getKey());

        this.interval = configuration.getLong(SnowKeys.INTERVAL.getKey());

        this.checkUpdate = false; //configuration.getBoolean(SnowKeys.CHECK_UPDATE.getKey());
        this.enable = configuration.getBoolean(SnowKeys.ENABLE.getKey());
        this.disableRain = configuration.getBoolean(SnowKeys.RAIN_DISABLE.getKey());

        this.enabledWorlds = configuration.getStringList(SnowKeys.ENABLED_WORLDS.getKey());
    }
}
