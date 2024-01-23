package org.example.interfaces;

public interface ResultFileParams extends Observable {
    void setIntFilename(String intFilename);

    void setFloatFilename(String floatFilename);

    void setStringFilename(String stringFilename);

    void setOutputPath(String path);

    void setAddToExistingFiles(boolean value);
}
