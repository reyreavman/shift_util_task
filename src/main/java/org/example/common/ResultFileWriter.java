package org.example.common;

import org.example.interfaces.ResultFileParams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ResultFileWriter implements ResultFileParams {
    private final HashMap<StringType, BufferedWriter> writersMap = new HashMap<>();
    private String intFilename;
    private String floatFilename;
    private String stringFilename;
    private BufferedWriter intFileWriter;
    private BufferedWriter floatFileWriter;
    private BufferedWriter stringFileWriter;
    private String outputPath;
    private boolean addToExistingFile;

    @Override
    public void setIntFilename(String intFilename) {
//        this.intFileWriter = new BufferedWriter(new FileWriter())
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
    public void setAddToExistingFiles(boolean value) {
        this.addToExistingFile = value;
    }

    public void write(StringType stringType, String string) throws IOException {
//        if (this.writersMap.get())
        this.writersMap.get(stringType).write(string + "\n");
        System.out.println(outputPath);
    }

    public void initWriters() {
        try {
            if (this.addToExistingFile) {
                intFileWriter = new BufferedWriter(new FileWriter(outputPath + this.intFilename, true));
                floatFileWriter = new BufferedWriter(new FileWriter(outputPath + this.floatFilename, true));
                stringFileWriter = new BufferedWriter(new FileWriter(outputPath + this.stringFilename, true));
            } else {
                intFileWriter = new BufferedWriter(new FileWriter(outputPath + this.intFilename, false));
                floatFileWriter = new BufferedWriter(new FileWriter(outputPath + this.floatFilename, false));
                stringFileWriter = new BufferedWriter(new FileWriter(outputPath + this.stringFilename, false));
            }
            this.writersMap.put(StringType.INT, this.intFileWriter);
            this.writersMap.put(StringType.FLOAT, this.floatFileWriter);
            this.writersMap.put(StringType.STRING, this.stringFileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeWriters() {
        try {
            this.intFileWriter.close();
            this.floatFileWriter.close();
            this.stringFileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}