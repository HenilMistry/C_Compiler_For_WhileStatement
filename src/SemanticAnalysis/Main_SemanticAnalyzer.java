package SemanticAnalysis;

import Lexer.LexicalAnalyzer;
import Lexer.Token;
import Parser.Node;
import Parser.SyntaxAnalyzer;

import java.util.List;

public class Main_SemanticAnalyzer {
    public static void main(String[] args) {
        String sourceCode = "int y; while (x < 10) { x = x + 1; }";
        LexicalAnalyzer lexer = new LexicalAnalyzer(sourceCode);
        List<Token> tokens = lexer.tokenize();
        SyntaxAnalyzer parser = new SyntaxAnalyzer(tokens);
        Node syntaxTree = parser.parse();

        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        semanticAnalyzer.performSemanticAnalysis(syntaxTree);

        // Further processing of the syntax tree, code generation, etc.
    }
}
