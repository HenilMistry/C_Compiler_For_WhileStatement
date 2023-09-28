package Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private String input;
    private int position;
    private int line;
    private List<Token> tokens;

    public LexicalAnalyzer(String input) {
        this.input = input;
        this.position = 0;
        this.line = 1;
        this.tokens = new ArrayList<>();
    }

    public List<Token> tokenize() {
        while (position < input.length()) {
            char currentChar = input.charAt(position);

            // Skip whitespace
            if (Character.isWhitespace(currentChar)) {
                if (currentChar == '\n') {
                    line++;
                }
                position++;
                continue;
            }

            // Match keywords and identifiers
            if (Character.isLetter(currentChar)) {
                StringBuilder lexeme = new StringBuilder();
                while (position < input.length() && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
                    lexeme.append(input.charAt(position));
                    position++;
                }
                String identifier = lexeme.toString();
                if ("while".equals(identifier)) {
                    tokens.add(new Token(TokenType.WHILE, identifier, line));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, identifier, line));
                }
                continue;
            }

            // Match integer literals
            if (Character.isDigit(currentChar)) {
                StringBuilder lexeme = new StringBuilder();
                while (position < input.length() && Character.isDigit(input.charAt(position))) {
                    lexeme.append(input.charAt(position));
                    position++;
                }
                tokens.add(new Token(TokenType.INTEGER_LITERAL, lexeme.toString(), line));
                continue;
            }

            // Match individual symbols
            switch (currentChar) {
                case '(':
                    tokens.add(new Token(TokenType.LEFT_PAREN, "(", line));
                    position++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RIGHT_PAREN, ")", line));
                    position++;
                    break;
                case '{':
                    tokens.add(new Token(TokenType.LEFT_BRACE, "{", line));
                    position++;
                    break;
                case '}':
                    tokens.add(new Token(TokenType.RIGHT_BRACE, "}", line));
                    position++;
                    break;
                case ';':
                    tokens.add(new Token(TokenType.SEMICOLON, ";", line));
                    position++;
                    break;
                case '=':
                    tokens.add(new Token(TokenType.EQUALS, "=", line));
                    position++;
                    break;
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+", line));
                    position++;
                    break;
                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-", line));
                    position++;
                    break;
                case '*':
                    tokens.add(new Token(TokenType.TIMES, "*", line));
                    position++;
                    break;
                case '/':
                    tokens.add(new Token(TokenType.DIVIDE, "/", line));
                    position++;
                    break;
                case '<':
                    tokens.add(new Token(TokenType.LESS_THAN, "<", line));
                    position++;
                    break;
                case '>':
                    tokens.add(new Token(TokenType.GREATER_THAN, ">", line));
                    position++;
                    break;
                case '&':
                    tokens.add(new Token(TokenType.AND, "&", line));
                    position++;
                    break;
                case '|':
                    tokens.add(new Token(TokenType.OR, "|", line));
                    position++;
                    break;
                case '!':
                    tokens.add(new Token(TokenType.NOT, "!", line));
                    position++;
                    break;
                // Add cases for other symbols as needed
                default:
                    // Handle errors or unrecognized characters
                    System.err.println("Lexical error: Unexpected character '" + currentChar + "' on line " + line);
                    position++;
            }
        }

        // Add an EOF token to indicate the end of the input
        tokens.add(new Token(TokenType.EOF, "", line));

        return tokens;
    }
}
