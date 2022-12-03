package ua.xsandl3x.sxsnow.listeners;

import org.bukkit.World;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.configuration.impl.SnowConfig;
import java.util.List;

public final class WeatherListener implements Listener {

    private final SnowConfig snowConfig = Main.getInstance().getSnowConfig();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        World world = event.getPlayer().getWorld();

        if (!world.hasStorm())
            return;

        this.newStormAction(world, () -> world.setStorm(false));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWeatherChange(WeatherChangeEvent event) {
        this.newStormAction(event.getWorld(), () -> {
            if (event.toWeatherState())
                event.setCancelled(true);
        });
    }

    private void newStormAction(World world, Runnable runnable) {
        List<String> enabledWorlds = this.snowConfig.getEnabledWorlds();

        if (!(this.snowConfig.isDisableRain() && this.snowConfig.isEnable()))
            return;

        if (!enabledWorlds.contains(world.getName()))
            return;

        runnable.run();
    }
}
