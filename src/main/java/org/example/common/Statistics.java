package org.example.common;

import org.example.interfaces.StatHandler;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private final boolean fullSize;
    private int elementsCount = 0;
    private int intMin = Integer.MAX_VALUE;
    private int intMax = Integer.MIN_VALUE;
    private int intSum = 0;
    private int intAvg = 0;
    private int intCallsCount = 0;
    private float floatMin = Float.MAX_VALUE;
    private float floatMax = Float.MIN_VALUE;
    private float floatSum = 0;
    private float floatAvg = 0;
    private int floatCallsCount = 0;
    private int stringMinLength = Integer.MAX_VALUE;
    private int stringMaxLength = Integer.MIN_VALUE;
    private int stringCallsCount = 0;
    private StatHandler stringValueHandle = (string) -> {
        String res = string.trim();
        if (res.length() > this.stringMaxLength) this.stringMaxLength = res.length();
        if (res.length() < this.stringMinLength) this.stringMinLength = res.length();
        this.stringCallsCount += 1;
    };
    private StatHandler intValueHandle = (value) -> {
        int intValue = Integer.parseInt(value.trim());
        if (intValue > this.intMax) this.intMax = intValue;
        if (intValue < this.intMin) this.intMin = intValue;
        this.intSum += intValue;
        this.intCallsCount += 1;
        this.intAvg = this.intSum / this.intCallsCount;
    };
    private StatHandler floatValueHandle = (value) -> {
        float floatValue = Float.parseFloat(value.trim());
        if (floatValue - this.floatMax > 0.00000001) this.floatMax = floatValue;
        if (floatValue - this.floatMin < 0.00000001) this.floatMin = floatValue;
        this.floatSum += floatValue;
        this.floatCallsCount += 1;
        this.floatAvg = this.floatSum / this.floatCallsCount;
    };
    private final HashMap<StringType, StatHandler> typeMap = new HashMap<StringType, StatHandler>(Map.ofEntries(
            Map.entry(StringType.INT, intValueHandle),
            Map.entry(StringType.FLOAT, floatValueHandle),
            Map.entry(StringType.STRING, stringValueHandle)
    ));

    public Statistics(boolean fullSize) {
        this.fullSize = fullSize;
    }

    public void addNewTypeToHandle(StringType type, StatHandler handler) {
        this.typeMap.put(type, handler);
    }

    public void setIntValueHandle(StatHandler handler) {
        this.intValueHandle = handler;
    }

    public void setFloatValueHandle(StatHandler handler) {
        this.floatValueHandle = handler;
    }

    public void setStringValueHandle(StatHandler handler) {
        this.stringValueHandle = handler;
    }

    private void increaseElementsCount() {
        elementsCount += 1;
    }

    public void handle(StringType type, String value) {
        increaseElementsCount();
        if (this.fullSize) this.typeMap.get(type).handle(value);
    }

    @Override
    public String toString() {
        String shortStat = "number of items written in output files — %s%n".formatted(this.elementsCount);
        if (!this.fullSize) return shortStat;
        else {
            StringBuilder builder = new StringBuilder(shortStat);
            if (this.intCallsCount > 0) builder.append("Integers: count — %s, min — %s, max — %s, sumAll — %s, avg — %s%n".formatted(this.intCallsCount, this.intMin, this.intMax, this.intSum, this.intAvg));
            if (this.floatCallsCount > 0) builder.append("Floats: count — %s, min — %s, max — %s, sumAll — %s, avg — %s%n".formatted(this.floatCallsCount, this.floatMin, this.floatMax, this.floatSum, this.floatAvg));
            if (this.stringCallsCount > 0) builder.append("Strings: count — %s, minLength — %s, maxLength — %s%n".formatted(this.stringCallsCount, this.stringMinLength, this.stringMaxLength));
            return builder.toString();
        }
    }
}
