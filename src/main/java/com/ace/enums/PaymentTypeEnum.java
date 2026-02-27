package com.ace.enums;

public enum PaymentTypeEnum {
    WITHDRAW("Withdraw"),
    DEPOSIT("Deposit");

    private final String name;

    PaymentTypeEnum(final String name) {
        this.name = name;
    }

    public static PaymentTypeEnum fromString(String type) {
        for (PaymentTypeEnum paymentType : PaymentTypeEnum.values()) {
            if (paymentType.name().equalsIgnoreCase(type)) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("No enum constant for type: " + type);
    }
}
