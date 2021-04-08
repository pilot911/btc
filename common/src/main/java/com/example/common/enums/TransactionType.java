package com.example.common.enums;

public enum TransactionType {

    DEPOSIT(1),
    BONUS(3);

    private final int type;

    TransactionType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }
}
