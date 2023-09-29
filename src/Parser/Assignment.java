package Parser;

public class Assignment extends Statement {
    public Identifier variable;
    public Expression value;

    public Assignment(Identifier variable, Expression value) {
        this.variable = variable;
        this.value = value;
    }
}
