package ua.xsandl3x.sxsnow.storage.impl.types;

import ua.xsandl3x.sxsnow.cache.Cache;
import ua.xsandl3x.sxsnow.player.SnowPlayer;
import ua.xsandl3x.sxsnow.storage.sql.MySQL;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.sql.*;
import java.util.logging.Level;

public class SnowMySQL extends MySQL {

    public SnowMySQL(String name, String host, String port, String user, String password) {
        super(name, host, port, user, password);
    }

    public void createTable() {
        this.execute("CREATE TABLE IF NOT EXISTS `players` (`ID` INT(16) PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(16) NOT NULL UNIQUE, `enablesnow` BOOLEAN)");
    }

    public void update(String name) {
        SnowPlayer snowPlayer = (SnowPlayer) Cache.getCachedData().getCachedObject(name);

        String value = snowPlayer.isEnableSnow() ? "1" : "0";

        this.execute("UPDATE `players` SET `enablesnow`='%s' WHERE `name`='%s'", value, snowPlayer.getName());
    }

    public void upload(String name) {
        SnowPlayer snowPlayer = new SnowPlayer(name);

        boolean enableSnow = true;

        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `players` WHERE `name`='" + snowPlayer.getName() + "'");

            if (resultSet.next()) {
                enableSnow = resultSet.getString("enablesnow").equals("1");
            }
            else {
                this.execute("INSERT INTO `players` (`name`, `enablesnow`) VALUES ('%s', '%s');", snowPlayer.getName(), 1);
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
