package ua.xsandl3x.sxsnow.snow.manager;

import lombok.*;
import lombok.experimental.*;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.cache.Cache;
import ua.xsandl3x.sxsnow.configuration.impl.SnowConfig;
import ua.xsandl3x.sxsnow.interfaces.*;
import ua.xsandl3x.sxsnow.snow.impl.Snow;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class SnowManager implements ILoadable, IUnloadable {

    private Main instance;
    @NonFinal
    private Snow snow;

    @Setter
    @Getter
    @NonFinal
    private boolean snowEnable;

    @Override
    public void load() {
        SnowConfig snowConfig = instance.getSnowConfig();

        this.setSnowEnable(snowConfig.isEnable());

        if (!this.isSnowEnable())
            return;

        this.snow = new Snow(this.instance,
                snowConfig.getEnabledWorlds(),
                snowConfig.getInterval(),
                snowConfig.getRadius(),
                snowConfig.getAmount());

        this.snow.load();
    }

    @Override
    public void unload() {
        Cache.getCachedData().clearCacheData();
        this.snow.unload();
    }
}
