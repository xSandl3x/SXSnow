package ua.xsandl3x.sxsnow.storage.sql;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.storage.Database;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.sql.*;
import java.util.concurrent.Executors;
import java.util.logging.Level;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class SQLite extends Database {

    private Main instance;
    private String name;

    @Override
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();

            String url = String.format("jdbc:sqlite://%s/%s.db", this.instance.getDataFolder().getAbsolutePath(), this.name);

            Connection connection = DriverManager.getConnection(url);

            this.setExecutor(Executors.newSingleThreadExecutor());
            this.setConnection(connection);
            this.setExecuteConnection(this.getConnection());
        }
        catch(Exception ex) {
            Utils.sendLog(Level.SEVERE, "&4Failed SQLite connection: %s", ex.getMessage());
        }
    }
}
