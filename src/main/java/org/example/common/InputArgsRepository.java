package org.example.common;

import org.example.interfaces.ArgsRepository;

import java.util.ArrayList;

public class InputArgsRepository implements ArgsRepository {
    private final ArrayList<String> inputFiles = new ArrayList<>();
    private String intFilename = "integers.txt";
    private String floatsFilename = "floats.txt";
    private String stringsFilename = "strings.txt";
    private String outputPath = System.getProperty("user.dir");
    private String outputPrefix;
    private boolean addToExistingFiles;
    private boolean fullStatistics;

    @Override
    public void setFullStat(boolean value) {
        this.fullStatistics = value;
//        System.out.printf("fullStat called!: %s%n", value);
    }

    @Override
    public void setOutputToExistingFile(boolean value) {
        this.addToExistingFiles = value;
//        System.out.printf("outputToExistingFile called!: %s%n", value);
    }

    @Override
    public void addInputFilename(String filename) {
            this.inputFiles.add(filename);
//            System.out.printf("addInputFilename called!: %s%n", filename);
    }

    public String getOutputPrefix() {
        return outputPrefix;
    }

    @Override
    public void setOutputPrefix(String prefix) {
        this.outputPrefix = prefix;
        this.intFilename = prefix + this.intFilename;
        this.floatsFilename = prefix + this.floatsFilename;
        this.stringsFilename = prefix + this.stringsFilename;;
    }

    public boolean isAddToExistingFiles() {
        return addToExistingFiles;
    }

    public boolean isFullStatistics() {
        return fullStatistics;
    }

    public String getOutputPath() {
        return outputPath;
    }

    @Override
    public void setOutputPath(String path) {
        this.outputPath = path;
    }

    public String getIntFilename() {
        return intFilename;
    }

    public String getFloatsFilename() {
        return floatsFilename;
    }

    public String getStringsFilename() {
        return stringsFilename;
    }

    public ArrayList<String> getInputFilenames() {
        return new ArrayList<>(this.inputFiles);
    }

    public String getFilename(int index) {
        return this.inputFiles.get(index);
    }
}
