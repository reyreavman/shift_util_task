package org.example.common;

import org.example.interfaces.ResultFileParams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ResultFileWriter implements ResultFileParams {
    private final HashMap<StringType, BufferedWriter> writersMap = new HashMap<>();
    private String intFilename;
    private String floatFilename;
    private String stringFilename;
    private String outputPath;
    private boolean addToExistingFile;

    @Override
    public void setIntFilename(String intFilename) {
        this.intFilename = intFilename;
    }

    @Override
    public void setFloatFilename(String floatFilename) {
        this.floatFilename = floatFilename;
    }

    @Override
    public void setStringFilename(String stringFilename) {
        this.stringFilename = stringFilename;
    }

    @Override
    public void setOutputPath(String outputPath) {
        if (!outputPath.endsWith("\\")) outputPath = outputPath.concat("\\");
        this.outputPath = outputPath;
    }

    @Override
    public void setWriteToExistingFiles(boolean value) {
        this.addToExistingFile = value;
    }

    public void write(StringType stringType, String string) throws IOException {
        BufferedWriter writer = this.writersMap.get(stringType);
        if (writer == null) writer = initilizeWriter(stringType);
        writer.write(string + "\n");
    }

    public void closeWriters() {
        for (StringType stringType : StringType.values()) {
            if (this.writersMap.get(stringType) != null) {
                try {
                    this.writersMap.get(stringType).close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private BufferedWriter initilizeWriter(StringType stringType) throws IOException {
        String allPath;
        if (stringType == StringType.INT) allPath = outputPath + intFilename;
        else if (stringType == StringType.FLOAT) allPath = outputPath + floatFilename;
        else allPath = outputPath + stringFilename;
        this.writersMap.put(stringType, new BufferedWriter(new FileWriter(allPath, this.addToExistingFile)));
        return this.writersMap.get(stringType);
    }
}