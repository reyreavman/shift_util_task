package org.example.common;

import org.example.interfaces.ResultFileParams;

import java.io.File;
import java.util.ArrayList;

public class CLIParamsRepository {
    private final ArrayList<File> inputFiles;
    private final String intsFilename;
    private final String floatsFilename;
    private final String stringFilename;
    private final String outputPath;
    private final String outputPrefix;
    private final boolean writeToExistingFiles;
    private final boolean fullStatNeeded;

    private CLIParamsRepository(Builder builder) {
        this.inputFiles = builder.inputFiles;
        this.intsFilename = builder.intsFilename;
        this.floatsFilename = builder.floatsFilename;
        this.stringFilename = builder.stringFilename;
        this.outputPath = builder.outputPath;
        this.outputPrefix = builder.outputPrefix;
        this.writeToExistingFiles = builder.writeToExistingFiles;
        this.fullStatNeeded = builder.fullStatNeeded;
    }

    public void transferToResultParams(ResultFileParams resultFileParams) {
        resultFileParams.setIntFilename(this.intsFilename);
        resultFileParams.setFloatFilename(this.floatsFilename);
        resultFileParams.setStringFilename(this.stringFilename);
        resultFileParams.setOutputPath(this.outputPath);
        resultFileParams.setWriteToExistingFiles(this.writeToExistingFiles);
    }

    public ArrayList<File> getInputFilenames() {
        return this.inputFiles;
    }

    public boolean isFullStatisticsNeeded() {
        return this.fullStatNeeded;
    }

    public static class Builder {
        private final ArrayList<File> inputFiles = new ArrayList<>();
        private String intsFilename = "integers.txt";
        private String floatsFilename = "floats.txt";
        private String stringFilename = "string.txt";
        private String outputPath = System.getProperty("user.dir");
        private String outputPrefix;
        private boolean writeToExistingFiles;
        private boolean fullStatNeeded;
        private ResultFileParams resultFileParams;

        public Builder setOutputPath(String path) {
            this.outputPath = path;
            return this;
        }

        public Builder setPrefix(String prefix) {
            this.outputPrefix = prefix;
            this.intsFilename = prefix.concat(this.intsFilename);
            this.floatsFilename = prefix.concat(this.floatsFilename);
            this.stringFilename = prefix.concat(this.stringFilename);
            return this;
        }

        public Builder writeOutputToExistingFiles(boolean value) {
            this.writeToExistingFiles = value;
            return this;
        }

        public Builder setFullStat(boolean value) {
            this.fullStatNeeded = value;
            return this;
        }

        public Builder setResultFileParams(ResultFileParams resultFileParams) {
            this.resultFileParams = resultFileParams;
            return this;
        }

        public void addInputFile(File file) {
            this.inputFiles.add(file);
        }

        public CLIParamsRepository build() {
            return new CLIParamsRepository(this);
        }
    }
}
