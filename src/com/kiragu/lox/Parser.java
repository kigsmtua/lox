package com.kiragu.lox;

import java.util.List;
import static com.kiragu.lox.TokenType.*;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Expr expression(){
        return equality();
    }

    private  Expr equality () {
        Expr expr = comparison();
        while (match(BANG_EQUAL, EQUAL_EQUAL)) {
            Token operator = previous();
            Expr right = comparison();
            expr = new Expr.Binary(expr, operator, right);
        }
        return  expr;
    }

    private Expr comparison() {
        Expr expr = term();
        while (match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)) {
            Token operator = previous();
            Expr right = term();
            // At this rate the gc will purge this
            expr = new  Expr.Binary(expr, operator, right);
        }
        return  expr;
    }

    // This is what happens when such works can be used
    private  Expr term() {
        Expr expr = factor();
        while (match(MINUS, PLUS)){
            Token operator = previous();
            Expr right = factor();
            expr = new Expr.Binary(expr, operator, right);;
        }
        return  expr;
    }

    private Expr factor() {
        Expr expr = unary();
        while (match(SLASH, STAR)) {
            Token operator = previous();
            Expr right  = unary();
            expr = new Expr.Binary(expr, operator, right);
        }
        return  expr;
    }

    private Expr unary() {
        if (match(BANG,MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new  Expr.Unary(operator, right);
        }
        /// This makes some sense as primary will terminate this values
        return  primary()
    }
    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }
    private boolean isAtEnd() {
        return peek().type == EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}
