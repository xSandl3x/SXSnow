package ua.xsandl3x.sxsnow.listeners;

import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.configuration.impl.SnowConfig;
import ua.xsandl3x.sxsnow.utils.Utils;

@FieldDefaults(makeFinal = true)
public final class UpdateListener implements Listener {

    private Main instance = Main.getInstance();

    private SnowConfig snowConfig = instance.getSnowConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.isOp() && snowConfig.isCheckUpdate() && instance.getUpdateCheck().isUpdateFound()) {
            Utils.sendMessage(player, snowConfig.getUpdate()
                    .replace("%prefix", snowConfig.getPrefix()));
        }
    }
}
