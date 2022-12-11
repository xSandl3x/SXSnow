package ua.xsandl3x.sxsnow.storage;

import com.sun.istack.internal.Nullable;
import lombok.*;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

@Setter(AccessLevel.PROTECTED)
public class DatabaseExecutor {

    private Connection executeConnection;
    private ExecutorService executor;

    protected void execute(String query, @Nullable Object... obj) {
        String queryFormat = String.format(query, obj);

        Runnable runnable = () -> {
            try {
                Statement statement = this.executeConnection.createStatement();
                statement.executeUpdate(queryFormat);
                statement.close();
            }
            catch (SQLException ex) {
                Utils.sendLog(Level.SEVERE, "&cSQL Query error: %S", ex.getMessage());
            }
        };
        this.executor.execute(runnable);
    }

    protected void executorShutdown() {
        this.executor.shutdownNow();
    }
}
