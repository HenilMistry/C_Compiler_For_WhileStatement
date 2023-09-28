package Parser;

public class WhileStatement extends Node {
    Expression condition;
    Block body;

    public WhileStatement(Expression condition, Block body) {
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public Block getBody() {
        return body;
    }
}
