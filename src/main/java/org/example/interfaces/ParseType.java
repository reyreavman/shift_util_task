package org.example.interfaces;

import org.example.common.CLIParamsRepository;

import java.util.Iterator;

@FunctionalInterface
public interface ParseType {
    String parse(CLIParamsRepository.Builder builder, Iterator<String> iterator);
}
