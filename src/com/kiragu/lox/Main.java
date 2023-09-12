package com.kiragu.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        if(args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
        System.out.println("Hello world!");
    }
    public static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        for (;;) {
            System.out.println("> ");
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            run(line);
            hadError = false;
        }
    }
    public  static  void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        /// This is just something
        /// Makes sense as such values come to use
        if (hadError) System.exit(65);
    }

    public  static void run(String source) {
        Scanner scanner = new Scanner();
        List<Token>  tokens = scanner.scanTokens();
        for (Token token: tokens) {
            // Does this even come close to using the values that
            System.out.println(tokens);
        }
    }

    // There was an error line  here
    static  void error(int line, String message) {
        report(line, "", message);
    }


    private static void report(int line, String where,
                               String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        /// Not sure what this variable is
        hadError = true;
    }
}