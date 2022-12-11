package ua.xsandl3x.sxsnow.snow;

import lombok.*;
import lombok.experimental.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.interfaces.*;
import ua.xsandl3x.sxsnow.player.SnowPlayer;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.util.*;
import java.util.logging.Level;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public abstract class AbstractSnow implements ILoadable, IUnloadable {

    private Main instance;
    @NonFinal
    private BukkitTask task;

    private long interval;

    private List<String> enabledWorlds;


    @Override
    public void load() {
        if (this.interval <= 0 || this.enabledWorlds.isEmpty())
            return;

        this.task = Bukkit.getScheduler().runTaskTimer(this.instance, () ->
                Bukkit.getOnlinePlayers().stream()
                        .filter(player -> SnowPlayer.of(player).isEnableSnow() && this.playerRequiredWorld(player))
                        .forEach(this::spawn), this.interval * 3, this.interval);
    }

    @Override
    public void unload() {
        try {
            this.task.cancel();
        }
        catch(Exception ex) {
            Utils.sendLog(Level.WARNING, "SnowTask is not initialized so it cannot be cancelled.");
        }
    }

    private boolean playerRequiredWorld(Player player) {
        return this.enabledWorlds.contains(player.getWorld().getName());
    }

    protected abstract void spawn(Player player);
}
