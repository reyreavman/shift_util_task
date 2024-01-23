package org.example.interfaces;

import java.util.HashMap;

@FunctionalInterface
public interface Handler {
    HashMap<String, Number> handle(String value);
}
