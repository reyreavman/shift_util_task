package org.example.common;

public class StringLineAnalyzer {
    private final static String INT_REGEX = "^-?\\d+$";
    private final static String FLOAT_REGEX = "^-?\\d+(\\.\\d+)?E?e?-?\\d*$";
    public static StringType analyze(String stringLine) {
        if (stringLine.matches(INT_REGEX)) {
            return StringType.INT;
        }
        if (stringLine.matches(FLOAT_REGEX)) {
            return StringType.FLOAT;
        }
        return StringType.STRING;
    }
}
