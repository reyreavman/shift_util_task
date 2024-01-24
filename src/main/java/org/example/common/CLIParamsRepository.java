package org.example.common;

import org.example.interfaces.ResultFileParams;

import java.util.ArrayList;
import java.util.List;

public class CLIParamsRepository {
    private final ArrayList<String> inputFiles;
    private final String intsFilename;
    private final String floatsFilename;
    private final String stringFilename;
    private final String outputPath;
    private final String outputPrefix;
    private final boolean writeToExistingFiles;
    private final boolean fullStatNeeded;
    private final ResultFileParams resultFileParams;

    private CLIParamsRepository(Builder builder) {
        this.inputFiles = builder.inputFiles;
        this.intsFilename = builder.intsFilename;
        this.floatsFilename = builder.floatsFilename;
        this.stringFilename = builder.stringFilename;
        this.outputPath = builder.outputPath;
        this.outputPrefix = builder.outputPrefix;
        this.writeToExistingFiles = builder.writeToExistingFiles;
        this.fullStatNeeded = builder.fullStatNeeded;
        this.resultFileParams = builder.resultFileParams;

        this.resultFileParams.setIntFilename(this.intsFilename);
        this.resultFileParams.setFloatFilename(this.floatsFilename);
        this.resultFileParams.setStringFilename(this.stringFilename);
        this.resultFileParams.setOutputPath(this.outputPath);
        this.resultFileParams.setWriteToExistingFiles(this.writeToExistingFiles);
    }

    public static class Builder {
        private final ArrayList<String> inputFiles;
        private String intsFilename = "integers.txt";
        private String floatsFilename = "floats.txt";
        private String stringFilename = "string.txt";
        private String outputPath = System.getProperty("user.dir");
        private String outputPrefix;
        private boolean writeToExistingFiles;
        private boolean fullStatNeeded;
        private ResultFileParams resultFileParams;

        public Builder(List<String> inputFiles) {
            this.inputFiles = new ArrayList<String>(inputFiles);
        }

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

        public CLIParamsRepository build() {
            return new CLIParamsRepository(this);
        }
    }
}
