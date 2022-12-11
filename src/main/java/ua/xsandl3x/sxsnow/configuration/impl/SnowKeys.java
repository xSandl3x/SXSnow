package ua.xsandl3x.sxsnow.configuration.impl;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum SnowKeys {

    ENABLED_WORLDS("Enabled-worlds"),

    SQL_TYPE("sql.type"),
    HOST("sql.data.host"),
    PORT("sql.data.port"),
    DATABASE("sql.data.database"),
    USERNAME("sql.data.username"),
    PASSWORD("sql.data.password"),

    CHECK_UPDATE("Check-update.enable"),

    ENABLE("Snow.enable"),
    REALISTIC("Snow.realistic"),
    RAIN_DISABLE("Snow.disable.rain-forever"),

    RADIUS("Settings.radius"),
    AMOUNT("Settings.amount"),
    INTERVAL("Settings.interval"),

    UPDATE_FOUND("Messages.update-found"),
    ACTION_ENABLED("Messages.action.enabled"),
    ACTION_DISABLED("Messages.action.disabled"),
    SNOW_ACTION("Messages.snow-action"),
    SNOW_PLAYER_ACTION("Messages.snow-player-action"),
    PREFIX("Messages.prefix"),
    INVALID_ARG("Messages.invalid-arg"),
    NO_PERMS("Messages.no-perms"),
    HELP_PAGE("Messages.help-page");


    private final String key;
}
