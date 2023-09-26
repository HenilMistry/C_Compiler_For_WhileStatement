package Lexer;

import java.util.List;

public class Main_Lexer {
    public static void main(String[] args) {
        String sourceCode = "while (a>b)\n" +
                "{\n" +
                "x = x+y ;\n" +
                "}";
        LexicalAnalyzer lexer = new LexicalAnalyzer(sourceCode);
        List<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.println(token.type + ": " + token.lexeme + " (Line " + token.line + ")");
        }
    }
}
