package org.example;

public class Main {
    public static void main(String[] args) {
        InputArgsRepository inputArgsRepository = new InputArgsRepository();
        ArgsParser argsParser = new ArgsParser(args).setRepo(inputArgsRepository).parse();
        
    }
}