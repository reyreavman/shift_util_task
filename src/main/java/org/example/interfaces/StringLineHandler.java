package org.example.interfaces;

import java.io.IOException;

@FunctionalInterface
public interface StringLineHandler {
    void invoke(String string);
}
