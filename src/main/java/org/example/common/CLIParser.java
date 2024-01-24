package org.example.common;

import org.example.interfaces.ParseType;

import java.io.File;
import java.util.*;

public class CLIParser {
    private final ParseType parseOutputPath = (builder, iterator) -> {
        if (iterator.hasNext()) {
            String value = iterator.next();
            if (value.startsWith("-") || value.isEmpty()) {
                System.out.println("-o: Output path entered incorrectly");
                System.exit(0);
            }
            builder.setOutputPath(value);
        }
        if (iterator.hasNext()) return iterator.next();
        return null;
    };
    private final ParseType parseOutputPrefix = (builder, iterator) -> {
        if (iterator.hasNext()) {
            String value = iterator.next();
            if (value.startsWith("-") || value.isEmpty()) {
                System.out.println("-p: Prefix entered incorrectly");
                System.exit(0);
            }
            builder.setPrefix(value);
        }
        if (iterator.hasNext()) return iterator.next();
        return null;
    };
    private final ParseType parseFlagWritToExistingFile = (builder, iterator) -> {
        builder.writeOutputToExistingFiles(true);
        if (iterator.hasNext()) return iterator.next();
        return null;
    };
    private final ParseType parseFullStat = (builder, iterator) -> {
        builder.setFullStat(true);
        if (iterator.hasNext()) return iterator.next();
        return null;
    };
    private final ParseType parseShortStat = ((builder, iterator) -> {
        builder.setFullStat(false);
        if (iterator.hasNext()) return iterator.next();
        return null;
    });
    private final ParseType parseInFiles = (builder, iterator) -> {
        if (iterator.hasNext()) {
            String value = iterator.next();
            File file = new File(value);
            if (!file.isFile()) {
                System.out.println("-in: The entered data does not represent an exist file(" + file.getAbsoluteFile() + ")");
                System.exit(0);
            }
            builder.addInputFile(file);
        }
        if (iterator.hasNext()) return iterator.next();
        return null;
    };
    private HashMap<String, ParseType> parseMap = new HashMap<>(Map.ofEntries(
            Map.entry("-o", parseOutputPath),
            Map.entry("-p", parseOutputPrefix),
            Map.entry("-a", parseFlagWritToExistingFile),
            Map.entry("-s", parseShortStat),
            Map.entry("-f", parseFullStat),
            Map.entry("-in", parseInFiles)
    ));

    public void addNewKeyParse(String key, ParseType parseType) {
        this.parseMap.put(key, parseType);
    }

    public void setParseMap(HashMap<String, ParseType> parseMap) {
        this.parseMap = parseMap;
    }

    public CLIParamsRepository parse(String[] args) {
        Iterator<String> iterator = Arrays.stream(args).iterator();
        CLIParamsRepository.Builder builder = new CLIParamsRepository.Builder();
        String key = null;
        try {
            key = iterator.next();
        } catch (NoSuchElementException e) {
            System.out.println("Input is empty, program terminated");
            System.exit(0);
        }
        while (key != null) {
            ParseType parseType = parseMap.get(key);
            if (parseType == null) {
                System.out.println("Unknown argument error: " + key);
                System.exit(0);
            }
            key = parseType.parse(builder, iterator);
        }
        return builder.build();
    }

}
