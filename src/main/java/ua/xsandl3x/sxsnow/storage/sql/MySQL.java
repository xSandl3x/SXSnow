package ua.xsandl3x.sxsnow.storage.sql;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.xsandl3x.sxsnow.storage.Database;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.sql.*;
import java.util.concurrent.Executors;
import java.util.logging.Level;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class MySQL extends Database {

    private String name;
    private String host;
    private String port;
    private String user;
    private String password;

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String additionOptions = "?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&maxReconnects=10";
            String url = String.format("jdbc:mysql://%s:%s/%s&user=%s&password=%s", this.host, this.port, this.name + additionOptions, this.user, this.password);

            Connection connection = DriverManager.getConnection(url);

            this.setExecutor(Executors.newSingleThreadExecutor());
            this.setConnection(connection);
            this.setExecuteConnection(this.getConnection());
        }
        catch (Exception ex) {
            Utils.sendLog(Level.SEVERE, "&4Failed MySQL connection: %s", ex.getMessage());
        }
    }
}
