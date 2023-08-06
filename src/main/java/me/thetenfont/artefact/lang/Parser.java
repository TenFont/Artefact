package me.thetenfont.artefact.lang;

import me.thetenfont.artefact.Artefact;
import me.thetenfont.artefact.lang.components.Compound;
import me.thetenfont.artefact.lang.components.Expression;
import me.thetenfont.artefact.lang.components.Statement;
import me.thetenfont.artefact.util.Util;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Map;

public class Parser {
    private final Tokenizer tokenizer;

    public Parser(String code) {
        this.tokenizer = new Tokenizer(code);
        tokenizer.getNextToken(this);
    }

    public Compound getParseResult() {
        return this.parseProgram();
    }

    /**
     * Main entry point
     * @return An abstract syntax tree repesenting the parsed program
     */
    public Compound parseProgram() {
        Compound compound = new Compound("Program", this.getCurrentLine(), this.getCurrentChar());
        compound.put("body", this.parseStatementList(null));
        return compound;
    }

    /**
     * A list of statements.
     * @param end The parser will stop parsing statements when this token is reached
     * @return The statement list
     */
    public ArrayList<Compound> parseStatementList(@Nullable String end) {
        Compound lookAhead = this.tokenizer.getLookAhead();
        ArrayList<Compound> statements = new ArrayList<>();
        while (this.tokenizer.hasNextToken() && (end == null || !lookAhead.get("type").equals(end))) {
            Compound statement = this.parseStatement();
            if (statement == null) return null;
            statements.add(statement);
        }
        return statements;
    }

    public Compound parseStatement() {
        String type = (String) this.tokenizer.getLookAhead().get("type");
        for (Map.Entry<String, Statement> entry : Artefact.getStatements().entrySet()) {
            if (type.equals(entry.getKey())) return entry.getValue().parse(this);
        }
        this.error("Could not find statement corresponding to token '" + type + "'.");
        return null;
    }

    public Compound parseExpression(Class<?> expectedType) {
        String type = (String) this.tokenizer.getLookAhead().get("type");
        for (Map.Entry<String, Expression<?>> entry : Artefact.getExpressions().entrySet()) {
            if (type.equals(entry.getKey())) {
                Expression<?> expression = entry.getValue();
                if (!expectedType.equals(expression.getReturnType())) {
                    this.error("Expected an expression of type '" + expectedType.getSimpleName() + "', but the '" + expression + "' expression is of type '" + expression.getReturnType().getSimpleName() + "' instead.");
                    return null;
                }
                return entry.getValue().parse(this);
            }
        }
        this.error("Could not find expression corresponding to token '" + type + "'.");
        return null;
    }

    public Compound consume(String token) {
        if (this.tokenizer.getLookAhead() == null) {
            this.error("Unexpected end of input, expected '" + token + "' instead.");
            return null;
        }
        Compound lookAhead = this.tokenizer.getLookAhead();
        String type = (String) lookAhead.get("type");
        if (!type.equals(token)) {
            this.error("Unexpected token '" + type + "', expected '" + token + "' instead.");
            return null;
        }
        this.tokenizer.getNextToken(this);
        return lookAhead;
    }

    public void error(String message) {
        Util.log("&cError at line &6" + getCurrentLine() + "&c, character &6" + getCurrentChar()
                + "\n&7" + message);
    }

    public int getCurrentLine() {
        return tokenizer.getCurrentLine();
    }

    public int getCurrentChar() {
        return tokenizer.getCurrentChar();
    }
}
