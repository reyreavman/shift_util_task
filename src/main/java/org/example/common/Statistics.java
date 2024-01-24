package org.example.common;

import org.example.interfaces.StatHandler;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private final boolean fullSize;
    private int elementsCount = 0;

    private final HashMap<StringType, StatHandler> typeMap = new HashMap<>(Map.ofEntries(
            Map.entry(StringType.INT, IntegerStat.valueHandle),
            Map.entry(StringType.FLOAT, FloatStat.valueHandle),
            Map.entry(StringType.STRING, StringStat.valueHandle)
    ));

    public Statistics(boolean fullSize) {
        this.fullSize = fullSize;
    }

    public void addNewTypeToHandle(StringType type, StatHandler handler) {
        this.typeMap.put(type, handler);
    }

    public void setIntValueHandle(StatHandler handler) {
        IntegerStat.valueHandle = handler;
    }

    public void setFloatValueHandle(StatHandler handler) {
        FloatStat.valueHandle = handler;
    }

    public void setStringValueHandle(StatHandler handler) {
        StringStat.valueHandle = handler;
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
        else if (this.fullSize) {
            StringBuilder builder = new StringBuilder(shortStat);
            if (IntegerStat.callCount > 0)
                builder.append("Integers: count — %s, min — %s, max — %s, sumAll — %s, avg — %s%n".formatted(IntegerStat.callCount, IntegerStat.min, IntegerStat.max, IntegerStat.sum, IntegerStat.avg));
            if (FloatStat.callsCount > 0)
                builder.append("Floats: count — %s, min — %s, max — %s, sumAll — %s, avg — %s%n".formatted(FloatStat.callsCount, FloatStat.min, FloatStat.max, FloatStat.sum, FloatStat.avg));
            if (StringStat.callsCount > 0)
                builder.append("Strings: count — %s, minLength — %s, maxLength — %s%n".formatted(StringStat.callsCount, StringStat.minLength, StringStat.maxLength));
            return builder.toString();
        } else return "";
    }

    private static class IntegerStat {
        private static int min = Integer.MAX_VALUE;
        private static int max = Integer.MIN_VALUE;
        private static int sum = 0;
        private static int avg = 0;
        private static int callCount = 0;
        private static StatHandler valueHandle = (value) -> {
            int intValue = Integer.parseInt(value.trim());
            if (intValue > max) max = intValue;
            if (intValue < min) min = intValue;
            sum += intValue;
            callCount += 1;
            avg = sum / callCount;
        };
    }

    private static class FloatStat {
        private static float min = Float.MAX_VALUE;
        private static float max = Float.MIN_VALUE;
        private static float sum = 0;
        private static float avg = 0;
        private static int callsCount = 0;
        private static StatHandler valueHandle = (value) -> {
            float floatValue = Float.parseFloat(value.trim());
            if (floatValue - max > 0.00000001) max = floatValue;
            if (floatValue - min < 0.00000001) min = floatValue;
            sum += floatValue;
            callsCount += 1;
            avg = sum / callsCount;
        };
    }

    private static class StringStat {
        private static int minLength = Integer.MAX_VALUE;
        private static int maxLength = Integer.MIN_VALUE;
        private static int callsCount = 0;
        private static StatHandler valueHandle = (string) -> {
            String res = string.trim();
            if (res.length() > maxLength) maxLength = res.length();
            if (res.length() < minLength) minLength = res.length();
            callsCount += 1;
        };
    }
}
