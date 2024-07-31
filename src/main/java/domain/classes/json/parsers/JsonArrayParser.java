package domain.classes.json.parsers;

import domain.abstraction.JsonPrimitive;
import domain.abstraction.JsonPrimitiveParser;
import domain.classes.exceptions.JsonArrayParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonArray;

import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;

public class JsonArrayParser extends JsonPrimitiveParser<JsonArray> {
    @Override
    public JsonArray parse(PushbackReader reader) throws JsonParserException {
        List<JsonPrimitive<?>> items = new ArrayList<>();

        int c;
        while ((c = getc(reader)) != -1) {
            System.out.println("ARRAY: " + (char)c);
            if (c == ']') {
                return new JsonArray(items);
            }

            System.out.println("START PARSE ARRAY ELEMENT");
            items.add((JsonPrimitive<?>) getParser(c).parse(reader));

            System.out.println("STOP PARSE ARRAY ELEMENT");
            while ((c = getc(reader)) != ',' && c != ']' && c != -1) {
                if (!Character.isWhitespace((char) c)) {
                    throw new JsonArrayParserException("Expected comma or closing bracket in array");
                }
            }

            if (c == ']') {
                return new JsonArray(items);
            }
        }

        throw new JsonArrayParserException("Unexpected end of json array");
    }
}
