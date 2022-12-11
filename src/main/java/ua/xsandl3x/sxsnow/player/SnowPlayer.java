package ua.xsandl3x.sxsnow.player;

import lombok.*;
import lombok.experimental.*;
import org.bukkit.entity.Player;
import ua.xsandl3x.sxsnow.cache.Cache;
import java.util.Map;

@Data
@FieldDefaults(makeFinal = true)
public class SnowPlayer {

    private String name;
    @NonFinal
    private boolean enableSnow;

    public static SnowPlayer of(Player player) {
        Map<String, Object> cacheMap = Cache.getCachedData().getCacheMap();

        return (SnowPlayer) cacheMap.computeIfAbsent(player.getName(), snowPlayer -> new SnowPlayer(player.getName()));
    }
}
