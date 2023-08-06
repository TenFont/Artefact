package me.thetenfont.artefact.lang.elements.expressions;

import me.thetenfont.artefact.lang.Interpreter;
import me.thetenfont.artefact.lang.Parser;
import me.thetenfont.artefact.lang.components.Compound;
import me.thetenfont.artefact.lang.components.Expression;
import org.jetbrains.annotations.NotNull;

public class StringLiteralExpression extends Expression<String> {
    @Override
    public String evaluate(@NotNull Interpreter interpreter, @NotNull Compound compound) {
        return "yep, seems like a string";
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull Compound parse(@NotNull Parser parser) {
        Compound compound = new Compound("StringLiteral", parser.getCurrentLine(), parser.getCurrentChar());
        Compound token = parser.consume("STRING");
        String value = (String) token.get("value");
        compound.put("value", value.substring(1, value.length()-1));
        return compound;
    }

    @Override
    public String toString() {
        return "String Literal";
    }
}
