package org.example;

import java.util.ArrayList;
import java.util.List;

public class InputStorage {
    private final List<String> inputList;

    public InputStorage(String[] inputList) {
        this.inputList = List.of(inputList);
    }

    public List<String> returnInput() {
        return new ArrayList<>(inputList);
    }
}
