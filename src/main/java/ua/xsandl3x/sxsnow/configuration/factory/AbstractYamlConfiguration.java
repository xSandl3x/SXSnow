package ua.xsandl3x.sxsnow.configuration.factory;

import lombok.*;
import lombok.experimental.*;
import org.bukkit.configuration.file.*;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.configuration.IConfiguration;
import java.io.File;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public abstract class AbstractYamlConfiguration implements IConfiguration {

    private Main instance;
    @NonFinal
    private File file;
    @NonFinal
    private FileConfiguration fileConfiguration;

    private String configurationName;


    @Override
    public void load() {
        this.file = new File(this.instance.getDataFolder() + "/" + this.configurationName + ".yml");

        if (!this.file.exists())
            this.instance.saveResource(this.file.getName(), false);

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        this.configure(this.fileConfiguration);
    }

    @SneakyThrows
    @Override
    public void save() {
        this.fileConfiguration.save(this.file);
    }

    @Override
    public FileConfiguration getFile() {
        return this.fileConfiguration;
    }

    protected abstract void configure(FileConfiguration configuration);
}
