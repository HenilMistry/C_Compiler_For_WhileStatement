package Parser;

public class Assignment extends Statement {
    Identifier variable;
    Expression value;

    public Assignment(Identifier variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }
}
