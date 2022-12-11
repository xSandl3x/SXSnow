package ua.xsandl3x.sxsnow.commands.manager;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import ua.xsandl3x.sxsnow.commands.SXCommand;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.logging.Level;

public class CommandManager {

    public static final CommandManager INSTANCE = new CommandManager();

    public void register(@NonNull SXCommand sxCommand) {
        this.newCommandAction(map -> map.register("SXCommand", sxCommand));
    }

    public void unregister(@NonNull SXCommand sxCommand) {
        this.newCommandAction(sxCommand::unregister);
    }

    private void newCommandAction(Predicate<CommandMap> predicate) {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);

            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            predicate.test(map);
        }
        catch (Exception ex) {
            Utils.sendLog(Level.SEVERE, "Command action error: %s", ex.getMessage());
        }
    }
}
