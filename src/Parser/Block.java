package Parser;

import java.util.List;

public class Block extends Node {
    List<Statement> statements;

    public Block(List<Statement> statements) {
        this.statements = statements;
    }
}
