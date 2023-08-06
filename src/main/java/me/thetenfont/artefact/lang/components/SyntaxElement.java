package me.thetenfont.artefact.lang.components;

import me.thetenfont.artefact.lang.Parser;
import org.jetbrains.annotations.NotNull;

public sealed abstract class SyntaxElement permits Statement, Expression {
    /**
     * Parses the element.
     *
     * @param parser The current parser instance
     * @return The parsed compound representing this element
     */
    public abstract @NotNull Compound parse(@NotNull Parser parser);
}
