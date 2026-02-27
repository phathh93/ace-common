package com.ace.enums;

public enum GameTypeEnum {
    none("none", 0),
    txh("Hold'em", 1),
    omh("Omaha", 2),
    phk("HongKong", 3),
    cpk("Capsa", 4),
    blackjack("Blackjack", 5),
    sng_txh("Hold'em Sit'n' Go", 6),
    sng_phk("HongKong Sit'n' Go", 7);

    private final String name;
    private final Integer value;

    GameTypeEnum(final String name,final Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public Integer getValue() {return value;}
}
