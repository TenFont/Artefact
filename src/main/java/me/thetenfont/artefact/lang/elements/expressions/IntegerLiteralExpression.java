package me.thetenfont.artefact.lang.elements.expressions;

import me.thetenfont.artefact.lang.Interpreter;
import me.thetenfont.artefact.lang.Parser;
import me.thetenfont.artefact.lang.components.Compound;
import me.thetenfont.artefact.lang.components.Expression;
import org.jetbrains.annotations.NotNull;

public class IntegerLiteralExpression extends Expression<Integer> {
    @Override
    public Integer evaluate(@NotNull Interpreter interpreter, @NotNull Compound compound) {
        return null;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull Compound parse(@NotNull Parser parser) {
        Compound compound = new Compound("DoubleLiteral", parser.getCurrentLine(), parser.getCurrentChar());
        compound.put("value", parser.consume("DOUBLE").get("value"));
        return compound;
    }

    @Override
    public String toString() {
        return "Integer Literal";
    }
}
