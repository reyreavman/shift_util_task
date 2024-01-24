package org.example.common;

import org.example.interfaces.StringLineHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InputFilesHandler {
    private final ArrayList<BufferedReader> fileReaders = new ArrayList<>();
    private StringLineHandler stringLineHandler;

    public InputFilesHandler(List<File> filenames) {
        for (File filename : filenames) {
            this.addNewFilename(filename);
        }
    }

    public void addNewFilename(File filename) {
        try {
            fileReaders.add(new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8)));
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s%n", filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedReader getFileReader(int index) {
        return this.fileReaders.get(index);
    }

    public InputFilesHandler setStringLineHandler(StringLineHandler stringLineHandler) {
        this.stringLineHandler = stringLineHandler;
        return this;
    }

    public InputFilesHandler handle() throws IOException {
        for (BufferedReader reader : this.fileReaders) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                stringLineHandler.invoke(line);
            }
            reader.close();
        }
        return this;
    }
}
