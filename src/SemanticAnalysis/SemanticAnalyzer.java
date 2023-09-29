package SemanticAnalysis;

import Parser.*;

import java.util.*;

public class SemanticAnalyzer {
    private Map<String, Integer> symbolTable;

    public SemanticAnalyzer() {
        this.symbolTable = new HashMap<>();
    }

    public void analyze(Node node) {
        if (node instanceof WhileStatement) {
            analyzeWhileStatement((WhileStatement) node);
        } else if (node instanceof Block) {
            analyzeBlock((Block) node);
        } else if (node instanceof Assignment) {
            analyzeAssignment((Assignment) node);
        } else if (node instanceof BinaryExpression) {
            analyzeBinaryExpression((BinaryExpression) node);
        }
        // Add more cases for other node types as needed
    }

    private void analyzeWhileStatement(WhileStatement whileStatement) {
        // Analyze the condition expression
        analyze(whileStatement.getCondition());

        // Analyze the body of the while loop
        analyze(whileStatement.getBody());
    }

    private void analyzeBlock(Block block) {
        for (Statement statement : block.statements) {
            analyze(statement);
        }
    }

    private void analyzeAssignment(Assignment assignment) {
        // Analyze the identifier (variable)
        String variableName = assignment.variable.name;
        if (!symbolTable.containsKey(variableName)) {
            System.err.println("Semantic error (Line " + assignment.variable + "): Variable '" + variableName + "' not declared.");
        }

        // Analyze the value expression
        analyze(assignment.value);
    }

    private void analyzeBinaryExpression(BinaryExpression binaryExpression) {
        // Analyze the left and right expressions
        analyze(binaryExpression.left);
        analyze(binaryExpression.right);

        // Check type compatibility and perform type checking here
    }

    public void performSemanticAnalysis(Node syntaxTree) {
        analyze(syntaxTree);
    }

    public void declareVariable(String variableName, int line) {
        if (symbolTable.containsKey(variableName)) {
            System.err.println("Semantic error (Line " + line + "): Variable '" + variableName + "' already declared.");
        } else {
            symbolTable.put(variableName, line);
        }
    }
}
