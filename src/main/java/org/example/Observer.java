package org.example;

import java.util.List;

public interface Observer {
    void addInputFilename(String filename);
    void setOutputPath(String path);

    void setOutputPrefix(String prefix);

    void setOutputToExistingFile(boolean value);

    void setFullStat(boolean value);
}
