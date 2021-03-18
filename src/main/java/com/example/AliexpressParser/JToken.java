package com.example.AliexpressParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JToken {
    private final Map<String, Object> fields;
    private String json;
    private int index;

    public JToken(){
        this.fields = new HashMap<>();
    }

    public Object getField(String type){
        return fields.get(type);
    }

    public Long getFieldAsLong(String type){
        Long result = 0L;
        var obj = fields.get(type);
        if (obj instanceof Long){
            result = (Long) obj;
        }
        return result;
    }

    public String getFieldAsString(String type){
        String result = null;
        var obj = fields.get(type);
        if (obj instanceof String){
            result = (String) obj;
        }
        return result;
    }

    public Boolean getFieldAsBool(String type){
        Boolean result = false;
        var obj = fields.get(type);
        if (obj instanceof Boolean){
            result = (Boolean) obj;
        }
        return result;
    }

    public int write(String json, int startIndex) {
        this.json = json;
        this.index = startIndex;

        while (cur() != '}'){
            inc();
            if (cur() == '"')
                writeNewField();
        }

        return this.index;
    }

    private void writeNewField() {
        inc();
        var type = writeFieldType();
        while (cur() != ':'){
            inc();
        }
        inc();
        var val = writeFieldValue();
        fields.put(type, val);
    }

    private String writeFieldType() {
        StringBuilder type = new StringBuilder();
        while (cur() != '"'){
            type.append(cur());
            inc();
        }
        return type.toString();
    }

    private Object writeFieldValue() {
        Object type = null;
        while (cur() != ',' && cur() != '}'){
            if (cur() == '[')
                type = writeArray();
            if (cur() == '{')
                type = writeNewToken();
            if (cur() == '"')
                type = writeTextValue();
            if (Character.isDigit(cur()))
                type = writeNumberValue();
            if (cur() == 't' || cur() == 'f')
                type = writeBooleanValue();
            inc();
        }
        return type;
    }

    private ArrayList<JToken> writeArray() {
        ArrayList<JToken> tokens = new ArrayList<>();
        while (cur() != ']'){
            if (cur() == '{')
                tokens.add(writeNewToken());
            inc();
        }
        return tokens;
    }

    private JToken writeNewToken() {
        var token = new JToken();
        index = token.write(json, index);
        return token;
    }

    private String writeTextValue() {
        StringBuilder value = new StringBuilder();
        inc();
        while (prev() == '\\' || cur() != '"'){
            if (cur() != '\\')
                value.append(cur());
            inc();
        }
        return value.toString();
    }

    private long writeNumberValue() {
        StringBuilder value = new StringBuilder();
        while (next() != ',' && next() != '}'){
            value.append(cur());
            inc();
        }
        value.append(cur());
        return Long.parseLong(value.toString().replaceAll(" ", ""));
    }

    private Boolean writeBooleanValue() {
        StringBuilder value = new StringBuilder();
        while (next() != ',' && next() != '}'){
            value.append(cur());
            inc();
        }
        value.append(cur());
        return Boolean.parseBoolean(value.toString().replaceAll(" ", ""));
    }

    private void inc(){
        index++;
    }

    private char cur(){
        return json.charAt(index);
    }

    private char next(){
        return json.charAt(index + 1);
    }

    private char prev(){
        return json.charAt(index - 1);
    }
}
