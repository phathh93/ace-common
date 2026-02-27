package com.ace.enums;

import java.util.Arrays;
import java.util.Optional;

public enum WalletTypeEnum {
    main("main",1),
    p999("Poker999",2),
    apk("AcePoker",3),
    abj("Ace BlackJack",4),
    he("HE",5),
    kw("Kawaii Island",6),
    kwm("Kawaii Masion",7),
    kwp("Kawaii Puzzle",8),
    arc("Archer Hunter",9),
    kwf("Kawaii Fishing",10),
    ovl("Overleague",11);

    private final String name;
    private final int id;

    WalletTypeEnum(final String name,final int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getId() {return id;}

    public static Optional<WalletTypeEnum> getById(int id)
    {
        return Arrays.stream(values())
                .filter(wallet -> wallet.id == id)
                .findFirst();
    }
}
