package org.bcp.banking.exchange.rate.dto;

public enum EnumMessage {
    APPLICATION_ERROR(-1),
    BUSINESS_ERROR(0),
    OK(1);
    private final Integer value;

    EnumMessage(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}