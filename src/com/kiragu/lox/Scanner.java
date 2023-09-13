package com.kiragu.lox;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private  final  String source;

    private  final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        /// We take this and move the scanner along this lines
        this.source = source;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }
    private void scanToKen() {
        System.out.println("Scan tokens and build the lexer");
    }
    List<Token> scanToKens () {
        while (!isAtEnd ()) {
            start = current;
            scanToKen();
        }

        tokens.add(new Token(TokenType.EOF,"", null, line));
        return  tokens;
    }
}
