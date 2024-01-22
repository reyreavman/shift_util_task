package org.example.common;

import org.example.interfaces.ArgsRepository;
import org.example.interfaces.ParseType;

import java.util.HashMap;
import java.util.Map;

public class ArgsParser {
    private final String[] args;
    private ArgsRepository repo;
    private HashMap<String, ParseType> parseMap = new HashMap<String, ParseType>(Map.ofEntries(
            Map.entry("-o", path -> repo.setOutputPath(path)),
            Map.entry("-p", prefix -> repo.setOutputPrefix(prefix)),
            Map.entry("-a", outPutToExistingFile -> repo.setOutputToExistingFile(true)),
            Map.entry("-s", fullStat -> repo.setFullStat(false)),
            Map.entry("-f", fullStat -> repo.setFullStat(true))
    ));

    public ArgsParser(String[] args) {
        this.args = args;
    }

    public void addNewParseMode(Map.Entry<String, ParseType> parseModeEntry) {
        this.parseMap.put(parseModeEntry.getKey(), parseModeEntry.getValue());
    }

    public void setParseModes(HashMap<String, ParseType> parseMode) {
        this.parseMap = parseMode;
    }

    public ArgsParser setRepo(ArgsRepository repo) {
        this.repo = repo;
        return this;
    }

    public ArgsParser parse() {
        for (int i = 0; i < args.length; i++) {
            if (this.parseMap.containsKey(args[i])) {
                if (args[i].startsWith("-o") || args[i].startsWith("-p")) {
                    this.parseMap.get(args[i]).parse(args[i + 1]);
                    i++;
                } else this.parseMap.get(args[i]).parse(args[i]);
            } else this.repo.addInputFilename(args[i]);
        }
        return this;
    }
}
