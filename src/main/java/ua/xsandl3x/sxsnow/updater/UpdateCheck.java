package ua.xsandl3x.sxsnow.updater;

import lombok.*;
import lombok.experimental.*;
import org.bukkit.Bukkit;
import ua.xsandl3x.sxsnow.Main;
import ua.xsandl3x.sxsnow.utils.Utils;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UpdateCheck {

    private Main instance;

    @Getter
    @NonFinal
    private boolean updateFound = false;

    private int id;

    public void search() {
        String URL = "https://api.spigotmc.org/legacy/update.php?resource=" + this.id;
        String version = instance.getDescription().getVersion();

        if (!instance.getSnowConfig().isCheckUpdate())
            return;

        Bukkit.getScheduler().runTaskAsynchronously(this.instance, () -> {
            try {
                InputStream inputStream = new URL(URL).openStream();
                Scanner scanner = new Scanner(inputStream);

                if (scanner.hasNext()) {
                    if (!version.equals(scanner.next())) {
                        Utils.sendLog(Level.INFO, instance.getSnowConfig().getUpdate());
                        this.updateFound = true;
                    }
                }
            } catch (IOException ex) {
                Utils.sendLog(Level.SEVERE, "&4Update search error: %s", ex.getMessage());
            }
        });
    }
}
