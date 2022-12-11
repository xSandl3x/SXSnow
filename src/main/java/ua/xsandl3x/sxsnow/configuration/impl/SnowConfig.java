package ua.xsandl3x.sxsnow.configuration.impl;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.configuration.factory.AbstractYamlConfiguration;
import java.util.List;

@Getter
public class SnowConfig extends AbstractYamlConfiguration {

    private String prefix;
    private String update;
    private String noPerms;
    private String actionDisabled;
    private String actionEnabled;
    private String snowPlayerAction;
    private String snowAction;
    private String invalidArgs;
    private String sqlTypes;
    private String host;
    private String port;
    private String username;
    private String database;
    private String password;
    private int amount;
    private double radius;
    private long interval;
    private boolean checkUpdate;
    private boolean enable;
    private boolean disableRain;
    private boolean realistic;

    private List<String> helpPage;
    private List<String> enabledWorlds;

    public SnowConfig(Main instance) {
        super(instance, "config");
    }

    @Override
    protected void configure(FileConfiguration configuration) {
        this.sqlTypes = configuration.getString(SnowKeys.SQL_TYPE.getKey());
        this.username = configuration.getString(SnowKeys.USERNAME.getKey());
        this.host = configuration.getString(SnowKeys.HOST.getKey());
        this.port = configuration.getString(SnowKeys.PORT.getKey());
        this.database = configuration.getString(SnowKeys.DATABASE.getKey());
        this.password = configuration.getString(SnowKeys.PASSWORD.getKey());
        this.prefix = configuration.getString(SnowKeys.PREFIX.getKey());
        this.update = configuration.getString(SnowKeys.UPDATE_FOUND.getKey());
        this.noPerms = configuration.getString(SnowKeys.NO_PERMS.getKey());
        this.actionDisabled = configuration.getString(SnowKeys.ACTION_DISABLED.getKey());
        this.actionEnabled = configuration.getString(SnowKeys.ACTION_ENABLED.getKey());
        this.snowPlayerAction = configuration.getString(SnowKeys.SNOW_PLAYER_ACTION.getKey());
        this.snowAction = configuration.getString(SnowKeys.SNOW_ACTION.getKey());
        this.invalidArgs = configuration.getString(SnowKeys.INVALID_ARG.getKey());
        this.radius = configuration.getDouble(SnowKeys.RADIUS.getKey());
        this.amount = configuration.getInt(SnowKeys.AMOUNT.getKey());
        this.interval = configuration.getLong(SnowKeys.INTERVAL.getKey());
        this.checkUpdate = configuration.getBoolean(SnowKeys.CHECK_UPDATE.getKey());
        this.enable = configuration.getBoolean(SnowKeys.ENABLE.getKey());
        this.disableRain = configuration.getBoolean(SnowKeys.RAIN_DISABLE.getKey());
        this.realistic = configuration.getBoolean(SnowKeys.REALISTIC.getKey());

        this.helpPage = configuration.getStringList(SnowKeys.HELP_PAGE.getKey());
        this.enabledWorlds = configuration.getStringList(SnowKeys.ENABLED_WORLDS.getKey());
    }
}
