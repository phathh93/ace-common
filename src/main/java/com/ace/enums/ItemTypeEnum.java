package com.ace.enums;

import lombok.Getter;

public enum ItemTypeEnum {
    Money("Money", "money", "Money", true),
    Token("Token", "token","token", true),
    Ticket("Ticket", "ticket","Lucky Spin Ticket", true),
    Key("Key","key", "ChestBox key", true),
    RankTicket("RankTicket","rank_ticket", "Rank Ticket", true),
    RankFrame("RankFrame","rank_frame", "Rank Frame", false),
    RankNameTag("RankNameTag","rank_name_tag", "Rank Name Tag", false),
    ADS("ADS", "ads", "ADS", true),
    Item("Item", "Item", "Item", false);

    @Getter
    private final String key;

    @Getter
    private final String name;

    @Getter
    private final String columnName;

    @Getter
    private final boolean isNumber;

    ItemTypeEnum(final String key,  final String columnName, final String name, final boolean isNumber) {
        this.name = name;
        this.key = key;
        this.columnName = columnName;
        this.isNumber = isNumber;
    }
}
