package org.example.common;

import org.example.interfaces.ArgsRepository;
import org.example.interfaces.ResultFileParams;

import java.nio.file.Path;
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
    private ResultFileParams resultFileCreator;

    @Override
    public void setFullStat(boolean value) {
        this.fullStatistics = value;
    }

    @Override
    public void addInputFilename(String filename) {
        this.inputFiles.add(filename);
    }

    @Override
    public void setOutputPrefix(String prefix) {
        this.outputPrefix = prefix;

        this.intFilename = this.outputPrefix + this.intFilename;
        this.floatsFilename = this.outputPrefix + this.floatsFilename;
        this.stringsFilename = this.outputPrefix + this.stringsFilename;

        this.resultFileCreator.setIntFilename(this.intFilename);
        this.resultFileCreator.setFloatFilename(this.floatsFilename);
        this.resultFileCreator.setStringFilename(this.stringsFilename);
    }

    @Override
    public void setAddToExistingFiles(boolean value) {
        this.addToExistingFiles = value;
        this.resultFileCreator.setAddToExistingFiles(this.addToExistingFiles);
    }

    @Override
    public void setOutputPath(String path) {
        this.outputPath = path;
        this.resultFileCreator.setOutputPath(this.outputPath);
    }

    public ArrayList<String> getInputFilenames() {
        return new ArrayList<>(this.inputFiles);
    }

    public void setResultFileCreator(ResultFileParams resultFileCreator) {
        this.resultFileCreator = resultFileCreator;
        this.resultFileCreator.setIntFilename(this.intFilename);
        this.resultFileCreator.setFloatFilename(this.floatsFilename);
        this.resultFileCreator.setStringFilename(this.stringsFilename);
        this.resultFileCreator.setOutputPath(this.outputPath);
    }
}
