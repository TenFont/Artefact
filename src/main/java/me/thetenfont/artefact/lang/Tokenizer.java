package me.thetenfont.artefact.lang;

import lombok.Getter;
import me.thetenfont.artefact.Artefact;
import me.thetenfont.artefact.lang.components.Compound;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Tokenizer {
    private final String code;
    private int cursor, currentLine, currentChar;
    private Compound lookAhead;

    public Tokenizer(String code) {
        this.code = code;
        this.cursor = 0;
        this.currentLine = 1;
        this.currentChar = 0;
    }

    public Compound getNextToken(Parser parser) {
        this.lookAhead = null;

        if (!hasNextToken()) {
            return null;
        }

        String code = this.code.substring(cursor);
        if (code.startsWith("\n")) {
            this.cursor++;
            this.currentLine++;
            this.currentChar = 0;
            return getNextToken(parser);
        }
        Matcher matcher;
        for (Map.Entry<Pattern, String> token : Artefact.getTokens().entrySet()) {
            matcher = token.getKey().matcher(code);
            if (!matcher.find()) continue;
            int length = matcher.group().length();
            Compound compound = new Compound(token.getValue(), this.currentLine, this.currentChar);
            this.cursor += length;
            this.currentChar += length;
            if (token.getValue() == null) return getNextToken(parser);
            compound.put("value", matcher.group());
            return (this.lookAhead = compound);
        }
        parser.error("Invalid syntax - Could not find a corresponding token. " + code);
        return null;
    }

    public boolean hasNextToken() {
        return this.code.length() > this.cursor;
    }
}
