package org.example.common;

import org.example.interfaces.ResultFileParams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ResultFileWriter implements ResultFileParams {
    private final HashMap<StringType, BufferedWriter> writersMap = new HashMap<>();
    private BufferedWriter intFileWriter;
    private BufferedWriter floatFileWriter;
    private BufferedWriter stringFileWriter;
    private String intFilaname;
    private String floatFilename;
    private String stringFilename;
    private String outputPath;
    private boolean addToExistingFile;

    @Override
    public void setIntFilename(String intFilename) {
        this.intFilaname = intFilename;
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
        BufferedWriter writer = this.writersMap.get(stringType);
        if (writer == null) writer = initilizeWriter(stringType);
        writer.write(string + "\n");
    }

    public void closeWriters() {
        try {
            if (this.intFileWriter != null) this.intFileWriter.close();
            if (this.floatFileWriter != null) this.floatFileWriter.close();
            if (this.stringFileWriter != null) this.stringFileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedWriter initilizeWriter(StringType stringType) throws IOException {
        BufferedWriter bufferedWriter;
        if (stringType.toString().equals("INT")) {
            if (this.addToExistingFile)
                this.intFileWriter = new BufferedWriter(new FileWriter(outputPath + intFilaname, true));
            else this.intFileWriter = new BufferedWriter(new FileWriter(outputPath + intFilaname));
            bufferedWriter = this.intFileWriter;
        } else if (stringType.toString().equals("FLOAT")) {
            if (this.addToExistingFile)
                this.floatFileWriter = new BufferedWriter(new FileWriter(outputPath + floatFilename, true));
            else this.floatFileWriter = new BufferedWriter(new FileWriter(outputPath + floatFilename));
            bufferedWriter = this.floatFileWriter;
        } else {
            if (this.addToExistingFile)
                this.stringFileWriter = new BufferedWriter(new FileWriter(outputPath + stringFilename, true));
            else this.stringFileWriter = new BufferedWriter(new FileWriter(outputPath + stringFilename));
            bufferedWriter = this.stringFileWriter;
        }
        this.writersMap.put(stringType, bufferedWriter);
        return bufferedWriter;
    }
}