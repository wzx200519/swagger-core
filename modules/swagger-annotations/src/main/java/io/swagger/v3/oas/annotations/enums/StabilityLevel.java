package io.swagger.v3.oas.annotations.enums;

public enum StabilityLevel {
    EXPERIMENTAL("experimental"),
    STABLE("stable"),
    DEPRECATED("deprecated");

    private String value;

    StabilityLevel(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
