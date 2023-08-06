package me.thetenfont.artefact;

import lombok.Getter;
import me.thetenfont.artefact.lang.Parser;
import me.thetenfont.artefact.lang.components.Expression;
import me.thetenfont.artefact.lang.components.Statement;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.regex.Pattern;

public final class Artefact extends JavaPlugin {
    @Getter
    private static final HashMap<Pattern, String> tokens = new HashMap<>();
    @Getter
    private static final HashMap<String, Statement> statements = new HashMap<>();
    @Getter
    private static final HashMap<String, Expression<?>> expressions = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        ElementsLoader.loadElements();
        Parser parser = new Parser("test 2;");
        System.out.println(parser.getParseResult());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Register a new token, such as a keyword, symbol, etc.
     *
     * @param pattern The regex pattern that matches the token
     * @param type The name of the token - If type is null, then the token will be ignored by the tokenizer.
     */
    public static void registerToken(@NotNull String pattern, @Nullable String type) {
        tokens.put(Pattern.compile("^" + pattern), type);
    }

    /**
     * Register a new statement.
     *
     * @param token
     * @param statement
     */
    public static void registerStatement(@NotNull String token, @NotNull Statement statement) {
        statements.put(token, statement);
    }

    /**
     * Register a new expression.
     *
     * @param token
     * @param expression
     */
    public static <T> void registerExpression(@NotNull String token, @NotNull Expression<T> expression) {
        expressions.put(token, expression);
    }
}
