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
        Statistics statistics = new Statistics(true);

        StringLineHandler stringLineHandler = (stringLine) -> {
            try {
                StringType stringType = StringLineAnalyzer.analyze(stringLine);
                statistics.handle(stringType, stringLine);
                resultFileWriter.write(stringType, stringLine);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        InputFilesHandler inputFilesHandler = new InputFilesHandler(inputArgsRepository.getInputFilenames()).setStringLineHandler(stringLineHandler).handle();
        System.out.println(statistics.toString());
        resultFileWriter.closeWriters();
    }
}