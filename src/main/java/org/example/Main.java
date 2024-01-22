package org.example;

import org.example.common.ArgsParser;
import org.example.common.Foo;
import org.example.common.InputArgsRepository;
import org.example.common.StringLineAnalyzer;
import org.example.interfaces.Action;

import java.io.IOException;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        InputArgsRepository inputArgsRepository = new InputArgsRepository();
        ArgsParser argsParser = new ArgsParser(args).setRepo(inputArgsRepository).parse();
        Action action = StringLineAnalyzer::analyze;
        Foo foo = new Foo(inputArgsRepository.getInputFilenames()).setAction(action).doMyJob();
    }
}