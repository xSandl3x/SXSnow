package ua.xsandl3x.sxsnow.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import ua.xsandl3x.sxsnow.interfaces.ILoadable;

public interface IConfiguration extends ILoadable {

    FileConfiguration getFile();

    void reload();

    void save();

}
