package com.botscrewtest.console;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public enum InputType {
    STRING(input -> true, ""),
    INTEGER(StringUtils::isNumeric, "Error! Please enter a number");

    private String errorMessage;
    private Predicate<String> isValid;

    InputType(Predicate<String> value, String errorMessage) {
        this.errorMessage = errorMessage;
        this.isValid = value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Predicate<String> getIsValid() {
        return isValid;
    }
}
