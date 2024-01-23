package org.example;

import org.example.common.*;
import org.example.interfaces.StringLineHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputArgsRepository inputArgsRepository = new InputArgsRepository();
        ResultFileWriter resultFileWriter = new ResultFileWriter();
        inputArgsRepository.setResultFileWriter(resultFileWriter);
        ArgsParser argsParser = new ArgsParser(args).setRepo(inputArgsRepository).parse();

        StringLineHandler stringLineHandler = (stringLine) -> {
            try {
                StringType stringType = StringLineAnalyzer.analyze(stringLine);
                resultFileWriter.write(stringType, stringLine);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Foo foo = new Foo(inputArgsRepository.getInputFilenames()).setStringLineHandler(stringLineHandler).doMyJob();
        resultFileWriter.closeWriters();
    }
}