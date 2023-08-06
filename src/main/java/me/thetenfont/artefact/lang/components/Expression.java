package me.thetenfont.artefact.lang.components;

import me.thetenfont.artefact.lang.Interpreter;
import org.jetbrains.annotations.NotNull;

public abstract non-sealed class Expression<T> extends SyntaxElement {
    /**
     * Evaluates the expression and returns its value.
     *
     * @param interpreter The interpreter instance that is evaluating the expression
     * @param compound The parsed compound representing the expression
     * @return The value that the expression should yield
     */
    public abstract T evaluate(@NotNull Interpreter interpreter, @NotNull Compound compound);

    /**
     * Gets the return type of the expression.
     *
     * @return The type of the expression that is returned by {@link #evaluate(Interpreter, Compound)}
     */
    public abstract @NotNull Class<? extends T> getReturnType();
}
