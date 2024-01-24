package org.example;

import org.example.common.*;
import org.example.interfaces.StringLineHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CLIParamsRepository cliParamsRepository = new CLIParser().parse(args);
        ResultFileWriter resultFileWriter = new ResultFileWriter();
        cliParamsRepository.transferToResultParams(resultFileWriter);
        Statistics statistics = new Statistics(cliParamsRepository.isFullStatisticsNeeded());
        StringLineAnalyzer stringLineAnalyzer = new StringLineAnalyzer();

        StringLineHandler stringLineHandler = (stringLine) -> {
            try {
                StringType stringType = stringLineAnalyzer.analyzeStringLine(stringLine);
                statistics.handle(stringType, stringLine);
                resultFileWriter.write(stringType, stringLine);
            } catch (IOException e) {
                if (e.getClass() == FileNotFoundException.class) {
                    System.out.println("File not found: " + e.getLocalizedMessage());
                    System.exit(0);
                }
            }
        };

        InputFilesHandler inputFilesHandler = new InputFilesHandler(cliParamsRepository.getInputFilenames()).setStringLineHandler(stringLineHandler).handle();
        System.out.println(statistics);
        resultFileWriter.closeWriters();
    }
}