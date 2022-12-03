package ua.xsandl3x.sxsnow.utils;

import com.sun.istack.internal.Nullable;
import lombok.experimental.UtilityClass;
import org.bukkit.*;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.configuration.impl.SnowConfig;

import java.util.logging.Level;

@UtilityClass
public class Utils {

    public void sendLog(Level level, String message, @Nullable Object... obj) {
        SnowConfig configuration = Main.getInstance().getSnowConfig();
        String coloredMessage = toColor(configuration.getPrefix() + message);

        Bukkit.getLogger().log(level, coloredMessage, obj);
    }

    public void sendLog(Level level, String[] messages, @Nullable Object... objs) {
        for (String message : messages)
             sendLog(level, message, objs);
    }

    public String toColor(String message) {
        return message.replace("&", "§");
    }
}
