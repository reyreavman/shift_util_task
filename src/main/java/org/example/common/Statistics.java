package org.example.common;

import org.example.interfaces.Handler;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private final HashMap<StringType, Handler> typeMap = new HashMap<StringType, Handler>(Map.ofEntries(
            Map.entry(StringType.INT, intValueHandle()),
            Map.entry(StringType.FLOAT, floatValueHandle()),
            Map.entry(StringType.STRING, stringValueHandler())
            ));
    private boolean fullSize;
    private int elementsCount = 0;
    private int intMin = Integer.MAX_VALUE;
    private int intMax = Integer.MIN_VALUE;
    private int intSum = 0;
    private int intAverage = 0;
    private int intCallsCount = 0;
    private float floatMin = Float.MAX_VALUE;
    private float floatMax = Float.MIN_VALUE;
    private float floatSum = 0;
    private float floatAverage = 0;
    private int floatCallsCount = 0;
    private int stringMinLength = Integer.MAX_VALUE;
    private int stringMaxLength = Integer.MIN_VALUE;

    public Statistics(boolean fullSize) {
        this.fullSize = fullSize;
    }

    private void stringValueHandler (String string) {
        if (string.length() > this.stringMaxLength) this.stringMaxLength = string.length();
        if (string.length() < this.stringMinLength) this.stringMinLength = string.length();
    }

    private void intValueHandle(int value) {
        if (value > this.intMax) this.intMax = value;
        if (value < this.intMin) this.intMin = value;
        this.intSum += value;
        this.intCallsCount += 1;
        this.intAverage = this.intSum / this.intCallsCount;
    }

    private void floatValueHandle(float value) {
        if (value - this.floatMax > 0.00000001) this.floatMax = value;
        if (value - this.floatMin < 0.00000001) this.floatMin = value;
        this.floatSum += value;
        this.floatCallsCount += 1;
        this.floatAverage = this.floatSum / this.floatCallsCount;
    }

    public void increaseElementsCount() {
        elementsCount += 1;
    }

    public void handle(StringType type, String string) {

    }
}
