package ua.xsandl3x.sxsnow.snow;

import lombok.*;
import lombok.experimental.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.interfaces.ILoadable;
import ua.xsandl3x.sxsnow.interfaces.IUnloadable;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public abstract class AbstractSnow implements ILoadable, IUnloadable {

    private Main instance;

    @NonFinal
    private BukkitTask taskId;
    private long interval;

    private List<String> enabledWorlds;


    @Override
    public void load() {
        if (this.interval <= 0 || this.enabledWorlds.isEmpty())
            return;

        this.taskId = Bukkit.getScheduler().runTaskTimer(this.instance, () ->
                Bukkit.getOnlinePlayers().stream()
                        .filter(player -> this.enabledWorlds.contains(player.getWorld().getName()))
                        .forEach(this::spawn), this.interval * 3, this.interval);
    }

    @Override
    public void unload() {
        Objects.requireNonNull(this.taskId, "SnowTask is not initialized so it cannot be cancelled.");
        this.taskId.cancel();
    }

    protected abstract void spawn(Player player);
}
