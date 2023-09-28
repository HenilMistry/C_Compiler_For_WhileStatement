package Lexer;

public class Token {
    public TokenType type;
    public String lexeme;
    public int line;

    public Token(TokenType type, String lexeme, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
    }
}
