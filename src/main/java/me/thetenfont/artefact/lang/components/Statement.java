package me.thetenfont.artefact.lang.components;

import me.thetenfont.artefact.lang.Interpreter;

public abstract non-sealed class Statement extends SyntaxElement {
    /**
     * Executes the statement.
     *
     * @param interpreter The interpreter instance that is executing the statement
     * @param compound The parsed compound representing the statement
     */
    public abstract void execute(Interpreter interpreter, Compound compound);
}
