package com.ace.enums.bonus;

public enum BonusTypeEnum {
    EVERY_DEPOSIT("Every Deposit"),
    FIRST_DEPOSIT("First Deposit"),
    SINGLE_USE("Single Use");

    private final String name;

    BonusTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BonusTypeEnum fromStringIgnoreCase(String value) {
        for (BonusTypeEnum type : BonusTypeEnum.values()) {
            if (type.name.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + BonusTypeEnum.class.getCanonicalName() + "." + value);
    }
}