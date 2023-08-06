package me.thetenfont.artefact.lang.elements.statements;

import me.thetenfont.artefact.lang.Interpreter;
import me.thetenfont.artefact.lang.Parser;
import me.thetenfont.artefact.lang.components.Compound;
import me.thetenfont.artefact.lang.components.Statement;
import me.thetenfont.artefact.util.Util;
import org.jetbrains.annotations.NotNull;

public class TestStatement extends Statement {
    @Override
    public void execute(Interpreter interpreter, Compound compound) {
        Util.log("executing the test statement!");
    }

    @Override
    public @NotNull Compound parse(@NotNull Parser parser) {
        Compound compound = new Compound("TestStatement", parser.getCurrentLine(), parser.getCurrentLine());
        parser.consume("test");
        compound.put("argument", parser.parseExpression(String.class));
        parser.consume(";");
        return compound;
    }
}
