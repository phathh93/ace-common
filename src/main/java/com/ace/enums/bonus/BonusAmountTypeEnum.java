package com.ace.enums.bonus;

public enum BonusAmountTypeEnum {
    PERCENT("Percentage"),
    FIXED("Fixed Amount");

    private final String name;

    BonusAmountTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BonusAmountTypeEnum fromStringIgnoreCase(String value) {
        for (BonusAmountTypeEnum type : BonusAmountTypeEnum.values()) {
            if (type.name.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + BonusAmountTypeEnum.class.getCanonicalName() + "." + value);
    }
}
