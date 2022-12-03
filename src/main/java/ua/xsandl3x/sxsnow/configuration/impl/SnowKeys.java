package ua.xsandl3x.sxsnow.configuration.impl;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum SnowKeys {

    PREFIX("Messages.prefix"),
    UPDATE_FOUND("Messages.update-found"),
    RADIUS("Settings.radius"),
    AMOUNT("Settings.amount"),
    INTERVAL("Settings.interval"),
    CHECK_UPDATE("Check-update.enable"),
    ENABLE("Snow.enable"),
    RAIN_DISABLE("Snow.disable.rain-forever"),
    ENABLED_WORLDS("Enabled-worlds");

    private final String key;
}
