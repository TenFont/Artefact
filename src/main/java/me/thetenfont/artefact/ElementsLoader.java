package me.thetenfont.artefact;

import me.thetenfont.artefact.lang.elements.expressions.IntegerLiteralExpression;
import me.thetenfont.artefact.lang.elements.expressions.StringLiteralExpression;
import me.thetenfont.artefact.lang.elements.statements.TestStatement;

public class ElementsLoader {
    static void loadElements() {
        // Tokens: Whitespace
        Artefact.registerToken("\\s+", null);
        // Tokens: Symbols
        Artefact.registerToken(";", ";");
        // Tokens: Keywords
        Artefact.registerToken("test", "test");
        // Tokens: Literals
        Artefact.registerToken("\"(?:[^\"]|\\\")*\"", "STRING");
        Artefact.registerToken("\\d+", "INTEGER");

        // Statements
        Artefact.registerStatement("test", new TestStatement());

        // Expressions
        Artefact.registerExpression("STRING", new StringLiteralExpression());
        Artefact.registerExpression("INTEGER", new IntegerLiteralExpression());
    }
}
