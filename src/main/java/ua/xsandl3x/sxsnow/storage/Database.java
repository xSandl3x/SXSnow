package ua.xsandl3x.sxsnow.storage;

import lombok.*;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.sql.*;
import java.util.logging.Level;

public abstract class Database extends DatabaseExecutor {

    @Setter
    private Connection connection;

    public void disconnect() {
        if (this.connection == null)
            return;

        try {
            this.connection.close();
            this.executorShutdown();
        }
        catch (SQLException ex) {
            Utils.sendLog(Level.SEVERE, "&cSQL Disconnect error: %s", ex.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed())
                this.connect();
        }
        catch (Exception ex) {
            Utils.sendLog(Level.SEVERE, "&cSQL Connection error: %s", ex.getMessage());
        }

        return this.connection;
    }

    public void createTable() { }

    public void update(String name) { }

    public void upload(String name) { }

    public abstract void connect();

}
