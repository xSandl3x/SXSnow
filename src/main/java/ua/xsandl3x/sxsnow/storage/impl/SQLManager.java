package ua.xsandl3x.sxsnow.storage.impl;

import lombok.RequiredArgsConstructor;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.interfaces.*;
import ua.xsandl3x.sxsnow.storage.Database;
import ua.xsandl3x.sxsnow.storage.impl.types.*;

@RequiredArgsConstructor
public class SQLManager implements ILoadable, IUnloadable {

    private final Main instance;
    private Database database;

    @Override
    public void load() {
        String sqlTypes = this.instance.getSnowConfig().getSqlTypes();
        String name = this.instance.getSnowConfig().getDatabase();
        String host = this.instance.getSnowConfig().getHost();
        String port = this.instance.getSnowConfig().getPort();
        String user = this.instance.getSnowConfig().getUsername();
        String password = this.instance.getSnowConfig().getPassword();

        this.database = !sqlTypes.equals("MySQL") ? new SnowSQLite(this.instance) : new SnowMySQL(name, host, port, user, password);
        this.database.connect();
        this.database.createTable();
    }

    @Override
    public void unload() {
        this.database.disconnect();
    }

    public Database getDatabase() {
        return this.database;
    }
}
