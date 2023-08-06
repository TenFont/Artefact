package me.thetenfont.artefact.lang.components;

import java.util.HashMap;

public class Compound extends HashMap<String, Object> {
    public Compound(String type, int lineNumber, int charNumber) {
        this.put("type", type);
        this.put("line", lineNumber);
        this.put("char", charNumber);
    }

    public Compound(String type, int lineNumber) {
        this.put("type", type);
        this.put("line", lineNumber);
    }
}
