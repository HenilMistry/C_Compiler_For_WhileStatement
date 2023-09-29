package Parser;

import Lexer.TokenType;
public class BinaryExpression extends Expression {
    public Expression left;
    TokenType operator;
    public Expression right;

    public BinaryExpression(Expression left, TokenType operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
