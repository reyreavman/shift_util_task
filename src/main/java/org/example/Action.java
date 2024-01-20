package org.example;

import java.io.IOException;

@FunctionalInterface
public interface Action {
    void invoke() throws IOException;
}
