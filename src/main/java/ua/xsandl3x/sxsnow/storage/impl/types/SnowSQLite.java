package ua.xsandl3x.sxsnow.storage.impl.types;

import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.cache.Cache;
import ua.xsandl3x.sxsnow.player.SnowPlayer;
import ua.xsandl3x.sxsnow.storage.sql.SQLite;
import ua.xsandl3x.sxsnow.utils.Utils;

import java.sql.*;
import java.util.logging.Level;

public class SnowSQLite extends SQLite {

    public SnowSQLite(Main instance) {
        super(instance, "SXData");
    }

    public void createTable() {
        this.execute("CREATE TABLE IF NOT EXISTS `players` (`ID` INT(16) PRIMARY KEY, `name` VARCHAR(16) NOT NULL UNIQUE, `enablesnow` BOOLEAN)");
    }

    public void update(String name) {
        SnowPlayer snowPlayer = (SnowPlayer) Cache.getCachedData().getCachedObject(name);

        this.execute("UPDATE `players` SET `enablesnow`='%s' WHERE `name`='%s'", snowPlayer.isEnableSnow(), snowPlayer.getName());
    }

    public void upload(String name) {
        SnowPlayer snowPlayer = new SnowPlayer(name);

        boolean enableSnow = true;

        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `players` WHERE `name`='" + snowPlayer.getName() + "'");

            if (resultSet.next()) {
                enableSnow = resultSet.getBoolean("enablesnow");
            }
            else {
                this.execute("INSERT INTO `players` (`name`, `enablesnow`) VALUES ('%s', '%s');", snowPlayer.getName(), snowPlayer.isEnableSnow());
            }
            resultSet.close();
            statement.close();
        }
        catch (Exception ex) {
            Utils.sendLog(Level.SEVERE, "&4Upload data error: %s", ex.getMessage());
        }

        snowPlayer.setEnableSnow(enableSnow);

        Cache.getCachedData().addCache(name, snowPlayer);
    }
}
