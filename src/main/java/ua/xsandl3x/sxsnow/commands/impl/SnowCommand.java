package ua.xsandl3x.sxsnow.commands.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.cache.Cache;
import ua.xsandl3x.sxsnow.commands.SXCommand;
import ua.xsandl3x.sxsnow.configuration.impl.SnowConfig;
import ua.xsandl3x.sxsnow.player.SnowPlayer;
import ua.xsandl3x.sxsnow.utils.Utils;

public class SnowCommand extends SXCommand {

    private final SnowConfig snowConfig = Main.getInstance().getSnowConfig();

    public SnowCommand() {
        super("sxs", "Main command", new String[] { "snow", "sxsnow" });
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {

        switch(args[0]) {

            case "toggle":
                this.changePlayerOption(sender);
                break;

            case "help":
                this.usage(sender);
                break;

            default:
                Utils.sendMessage(sender, snowConfig.getInvalidArgs());
                break;
        }
    }

    @Override
    protected void usage(CommandSender sender) {
        if (!sender.hasPermission("sxsnow.help")) {
            Utils.sendMessage(sender, this.snowConfig.getNoPerms().replace("%prefix", this.snowConfig.getPrefix()));
            return;
        }

        for (String line : this.snowConfig.getHelpPage()) {
            Utils.sendMessage(sender, line);
        }
     }

    private void changePlayerOption(CommandSender sender) {
        if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, "&cThis not available for the console.");
            return;
        }

        if (!sender.hasPermission("sxsnow.toggle")) {
            Utils.sendMessage(sender, this.snowConfig.getNoPerms().replace("%prefix", this.snowConfig.getPrefix()));
            return;
        }

        Player player = (Player) sender;
        SnowPlayer snowPlayer = SnowPlayer.of(player);

        boolean newValue = !snowPlayer.isEnableSnow();
        String action = newValue ? this.snowConfig.getActionEnabled() : this.snowConfig.getActionDisabled();

        snowPlayer.setEnableSnow(newValue);

        Utils.sendMessage(player, this.snowConfig.getSnowPlayerAction()
                .replace("%prefix", this.snowConfig.getPrefix())
                .replace("%action", action));

        Cache.getCachedData().changeCache(player.getName(), snowPlayer);
    }
}
