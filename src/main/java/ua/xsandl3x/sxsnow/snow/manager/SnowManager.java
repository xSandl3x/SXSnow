package ua.xsandl3x.sxsnow.snow.manager;

import lombok.*;
import lombok.experimental.*;
import ua.xsandl3x.sxsnow.Main;
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
                snowConfig.getInterval(),
                snowConfig.getEnabledWorlds(),
                snowConfig.getRadius(),
                snowConfig.getAmount());

        this.snow.load();
    }

    @Override
    public void unload() {
        this.snow.unload();
    }
}
