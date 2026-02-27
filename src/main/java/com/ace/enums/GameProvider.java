package com.ace.enums;

public enum GameProvider {
    Ace,
    Pragmatic,
    PGSoft,
    Evolution,
    Microgaming,
    Playtech,
    Saba,
    Habanero,
    SAGaming,
    Idnplay,
    All;

    public static GameProvider fromStringIgnoreCase(String value) {
        for (GameProvider provider : GameProvider.values()) {
            if (provider.name().equalsIgnoreCase(value)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("No enum constant " + GameProvider.class.getCanonicalName() + "." + value);
    }
}
