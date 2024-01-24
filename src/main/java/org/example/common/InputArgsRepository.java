package org.example.common;

import org.example.interfaces.ArgsRepository;
import org.example.interfaces.ResultFileParams;

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
    private ResultFileParams resultFileParams;

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

        this.resultFileParams.setIntFilename(this.intFilename);
        this.resultFileParams.setFloatFilename(this.floatsFilename);
        this.resultFileParams.setStringFilename(this.stringsFilename);
    }

    @Override
    public void setAddToExistingFiles(boolean value) {
        this.addToExistingFiles = value;
        this.resultFileParams.setWriteToExistingFiles(this.addToExistingFiles);
    }

    @Override
    public void setOutputPath(String path) {
        this.outputPath = path;
        this.resultFileParams.setOutputPath(this.outputPath);
    }

    public ArrayList<String> getInputFilenames() {
        return new ArrayList<>(this.inputFiles);
    }

    public boolean isFullStatisticsNeeded() {
        return this.fullStatistics;
    }

    public void setResultFileWriter(ResultFileParams resultFileWriter) {
        this.resultFileParams = resultFileWriter;
        transferDataToResulFileParams();
    }

    public void transferDataToResulFileParams() {
        this.resultFileParams.setIntFilename(this.intFilename);
        this.resultFileParams.setFloatFilename(this.floatsFilename);
        this.resultFileParams.setStringFilename(this.stringsFilename);
        this.resultFileParams.setOutputPath(this.outputPath);
    }
}
