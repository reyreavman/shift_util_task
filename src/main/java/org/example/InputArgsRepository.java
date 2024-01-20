package org.example;

import java.io.File;
import java.util.ArrayList;

public class InputArgsRepository implements Observer {
    private final ArrayList<File> inputFiles = new ArrayList<>();
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
        System.out.println("fullStat called!: %s".formatted(value));
    }

    @Override
    public void setOutputToExistingFile(boolean value) {
        this.addToExistingFiles = value;
        System.out.println("outputToExistinFile called!: %s".formatted(value));
    }

    @Override
    public void addInputFilename(String filename) {
        this.inputFiles.add(new File(filename));
        System.out.println("addInputFilename called!: %s".formatted(filename));
    }

    public String getOutputPrefix() {
        return outputPrefix;
    }

    @Override
    public void setOutputPrefix(String prefix) {
        this.outputPrefix = prefix;
        this.intFilename = prefix + this.intFilename;
        this.floatsFilename = prefix + this.floatsFilename;
        this.stringsFilename = prefix + this.stringsFilename;
        System.out.println("outputPrefix called!: %s".formatted(prefix));
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
        System.out.println("setOutputPath called!: %s".formatted(path));
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

    public ArrayList<File> getInputFiles() {
        return new ArrayList<>(this.inputFiles);
    }
}
