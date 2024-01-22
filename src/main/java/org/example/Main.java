package org.example;

import org.example.common.*;
import org.example.interfaces.Action;
import org.example.interfaces.ResultFileParams;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputArgsRepository inputArgsRepository = new InputArgsRepository();
        ResultFileWriter resultFileWriter = new ResultFileWriter();
        inputArgsRepository.setResultFileCreator(resultFileWriter);
        ArgsParser argsParser = new ArgsParser(args).setRepo(inputArgsRepository).parse();
        resultFileWriter.initWriters();
        Action action = (string) -> {
            StringType stringType = StringLineAnalyzer.analyze(string);
            resultFileWriter.write(stringType, string);
        };
        Foo foo = new Foo(inputArgsRepository.getInputFilenames()).setAction(action).doMyJob();
        resultFileWriter.closeWriters();
    }
}