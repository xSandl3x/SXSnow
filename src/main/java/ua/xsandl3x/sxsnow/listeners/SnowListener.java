package ua.xsandl3x.sxsnow.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.cache.Cache;
import ua.xsandl3x.sxsnow.storage.Database;

public final class SnowListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Cache.getCachedData().isCached(player.getName()))
            return;

        Database database = Main.getInstance().getSqlManager().getDatabase();

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () ->
                database.upload(player.getName()), 5L);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Database database = Main.getInstance().getSqlManager().getDatabase();
        database.update(player.getName());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();

        Database database = Main.getInstance().getSqlManager().getDatabase();
        database.update(player.getName());
    }
}
