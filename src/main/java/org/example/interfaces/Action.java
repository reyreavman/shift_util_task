package org.example.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface Action {
    void invoke(String string) throws IOException;
}
