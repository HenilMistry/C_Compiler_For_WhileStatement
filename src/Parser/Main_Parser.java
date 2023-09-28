package Parser;

import Lexer.*;

import java.util.List;

public class Main_Parser {
    public static void main(String[] args) {
        String sourceCode = "while (a=b)\n" +
                "{\n" +
                "a=x+y;\n" +
                "}";
        LexicalAnalyzer lexer = new LexicalAnalyzer(sourceCode);
        List<Token> tokens = lexer.tokenize();
        SyntaxAnalyzer parser = new SyntaxAnalyzer(tokens);
        Node syntaxTree = parser.parse();
    }
}
