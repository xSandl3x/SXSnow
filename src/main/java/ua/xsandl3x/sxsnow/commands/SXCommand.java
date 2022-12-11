package ua.xsandl3x.sxsnow.commands;

import org.bukkit.command.*;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.util.Arrays;
import java.util.logging.Level;

public abstract class SXCommand extends Command {

    public SXCommand(String commandName, String description, String[] aliases) {
        super(commandName, description, "/".concat(commandName), Arrays.asList(aliases));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 0) {
            this.usage(sender);
            return true;
        }

        try {
            this.execute(sender, args);
        }
        catch (Exception ex) {
            Utils.sendLog(Level.SEVERE, "&cCommand exucute error: %s", ex.getMessage());
        }
        return false;
    }

    protected void usage(CommandSender sender) { }

    protected abstract void execute(CommandSender sender, String[] args);
}
