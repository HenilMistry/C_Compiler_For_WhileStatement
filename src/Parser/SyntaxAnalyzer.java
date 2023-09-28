package Parser;

import Lexer.*;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyzer {
    private List<Token> tokens;
    private int currentTokenIndex;

    public SyntaxAnalyzer(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    public Node parse() {
        return parseWhileStatement();
    }

    private Node parseWhileStatement() {
        if (match(TokenType.WHILE)) {
            consume(TokenType.LEFT_PAREN);
            Expression condition = parseExpression();
            consume(TokenType.RIGHT_PAREN);
            Block body = parseBlock();
            return new WhileStatement(condition, body);
        }
        return null;
    }

    private Expression parseExpression() {
        return parseLogicalOrExpression();
    }

    private Expression parseLogicalOrExpression() {
        Expression left = parseLogicalAndExpression();
        while (match(TokenType.OR)) {
            TokenType operator = previous().type;
            Expression right = parseLogicalAndExpression();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parseLogicalAndExpression() {
        Expression left = parseEqualityExpression();
        while (match(TokenType.AND)) {
            TokenType operator = previous().type;
            Expression right = parseEqualityExpression();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parseEqualityExpression() {
        Expression left = parseRelationalExpression();
        while (match(TokenType.EQUALS, TokenType.NOT)) {
            TokenType operator = previous().type;
            Expression right = parseRelationalExpression();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parseRelationalExpression() {
        Expression left = parseAdditiveExpression();
        while (match(TokenType.LESS_THAN, TokenType.GREATER_THAN)) {
            TokenType operator = previous().type;
            Expression right = parseAdditiveExpression();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parseAdditiveExpression() {
        Expression left = parseMultiplicativeExpression();
        while (match(TokenType.PLUS, TokenType.MINUS)) {
            TokenType operator = previous().type;
            Expression right = parseMultiplicativeExpression();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parseMultiplicativeExpression() {
        Expression left = parsePrimaryExpression();
        while (match(TokenType.TIMES, TokenType.DIVIDE)) {
            TokenType operator = previous().type;
            Expression right = parsePrimaryExpression();
            left = new BinaryExpression(left, operator, right);
        }
        return left;
    }

    private Expression parsePrimaryExpression() {
        if (match(TokenType.IDENTIFIER)) {
            return new Identifier(previous().lexeme);
        }
        if (match(TokenType.INTEGER_LITERAL)) {
            return new IntegerLiteral(Integer.parseInt(previous().lexeme));
        }
        if (match(TokenType.LEFT_PAREN)) {
            Expression expression = parseExpression();
            consume(TokenType.RIGHT_PAREN);
            return expression;
        }
        return null; // Parsing error
    }

    private Block parseBlock() {
        consume(TokenType.LEFT_BRACE);
        List<Statement> statements = new ArrayList<>();
        while (!check(TokenType.RIGHT_BRACE) && !isAtEnd()) {
            Statement statement = parseStatement();
            if (statement != null) {
                statements.add(statement);
            }
        }
        consume(TokenType.RIGHT_BRACE);
        return new Block(statements);
    }

    private Statement parseStatement() {
        if (match(TokenType.IDENTIFIER)) {
            Identifier variable = new Identifier(previous().lexeme);
            consume(TokenType.EQUALS);
            Expression value = parseExpression();
            consume(TokenType.SEMICOLON);
            return new Assignment(variable, value);
        }
        return null; // Parsing error
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
        if (!isAtEnd()) currentTokenIndex++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(currentTokenIndex);
    }

    private Token previous() {
        return tokens.get(currentTokenIndex - 1);
    }

    private void consume(TokenType type) {
        if (check(type)) {
            advance();
        } else {
            // Handle parsing error
            System.err.println("Parsing error (Line " + peek().line + "): Expected " + type);
        }
    }
}
