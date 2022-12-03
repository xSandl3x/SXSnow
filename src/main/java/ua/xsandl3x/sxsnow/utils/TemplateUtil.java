package ua.xsandl3x.sxsnow.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.plugin.PluginDescriptionFile;
import ua.xsandl3x.sxsnow.Main;
import java.util.logging.Level;

@UtilityClass
public class TemplateUtil {

    private final PluginDescriptionFile description = Main.getInstance().getDescription();

    public void pluginStartMessage() {
        String lastModified = "01.12.2022";
        String authors = String.join(", ", description.getAuthors());

            Utils.sendLog(Level.INFO, new String[] {
                    "",
                    " &7{0} is enabled!",
                    " &7-> Plugin version: &f{1}",
                    " &7-> Author: &f{2}",
                    " &7-> Last modified: &f{3}",
                    ""
            }, description.getName(), description.getVersion(), authors, lastModified);
    }

    public void pluginStopMessage() {
        Utils.sendLog(Level.INFO, "&c{0} is disabled! Goodbye..", description.getName());
    }
}
