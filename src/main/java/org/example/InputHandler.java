package org.example;

import java.util.List;

public class InputHandler {
    private final List<String> inputFiles;
    private final String intFilename;
    private final String floatsFilename;
    private final String stringsFilename;
    private final String resultsPath;
    private final String outputFilesPrefix;
    private final boolean addToExistingPath;
    private final boolean completeStatistics;

    private InputHandler(Builder builder) {
        this.inputFiles = builder.inputFiles;
        this.intFilename = builder.intFilename;
        this.floatsFilename = builder.floatsFilename;
        this.stringsFilename = builder.stringsFilename;
        this.resultsPath = builder.resultsPath;
        this.outputFilesPrefix = builder.outputFilesPrefix;
        this.addToExistingPath = builder.addToExistingPath;
        this.completeStatistics = builder.completeStatistics;
    }

    public static class Builder {
        private final List<String> inputFiles;
        private String intFilename = "integers.txt";
        private String floatsFilename = "floats.txt";
        private String stringsFilename = "strings.txt";
        private String resultsPath;
        private String outputFilesPrefix;
        private boolean addToExistingPath;
        private boolean completeStatistics;

        public Builder(List<String> inputFiles) {
            this.inputFiles = inputFiles;
        }

        public Builder setResultsPath(String path) {
            this.resultsPath = path;
            return this;
        }

        public Builder outputFilePrefix(String prefix) {
            this.outputFilesPrefix = prefix;
            return this;
        }

        public Builder addToExistingPath(boolean value) {
            this.addToExistingPath = value;
            return this;
        }

        public Builder setCompleteStatistics(boolean value) {
            this.completeStatistics = value;
            return this;
        }

        public InputHandler build() {
            return new InputHandler(this);
        }
    }
}
