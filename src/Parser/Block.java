package Parser;

import java.util.List;

public class Block extends Node {
    public List<Statement> statements;

    public Block(List<Statement> statements) {
        this.statements = statements;
    }
}
