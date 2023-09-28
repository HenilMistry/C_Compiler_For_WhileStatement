package Parser;

import Lexer.TokenType;
public class BinaryExpression extends Expression {
    Expression left;
    TokenType operator;
    Expression right;

    public BinaryExpression(Expression left, TokenType operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
