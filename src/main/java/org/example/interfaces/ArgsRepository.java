package org.example.interfaces;

public interface ArgsRepository extends Observable {
    void addInputFilename(String filename);
    void setOutputPath(String path);

    void setOutputPrefix(String prefix);

    void setAddToExistingFiles(boolean value);

    void setFullStat(boolean value);
}
