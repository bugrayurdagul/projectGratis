package com.gratis.locators;

import com.gratis.locator_enum.LocatorTypeEnum;


public class Locator {
    private String value;
    private LocatorTypeEnum type;

    public Locator(String value, LocatorTypeEnum type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public LocatorTypeEnum getType() {
        return type;
    }
}
