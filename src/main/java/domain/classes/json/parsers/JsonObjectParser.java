package domain.classes.json.parsers;

import domain.abstraction.JsonPrimitive;
import domain.abstraction.JsonPrimitiveParser;
import domain.classes.exceptions.JsonObjectParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonObject;
import domain.classes.json.JsonString;

import java.io.PushbackReader;
import java.util.HashMap;
import java.util.Map;

public class JsonObjectParser extends JsonPrimitiveParser<JsonObject> {
    @Override
    public JsonObject parse(final PushbackReader reader) throws JsonParserException {
        Map<JsonString, JsonPrimitive<?>> properties = new HashMap<>();

        int c;
        while ((c = getc(reader)) != -1) {
            System.out.println("KEY: " + (char)c);

            if (c == ',') {
                continue;
            }
            if (c == '}') {
                return new JsonObject(properties);
            }
            if (c != '"') {
                throw new JsonObjectParserException("Unexpected character in object: " + (char)c);
            }

            JsonString key = new JsonStringParser().parse(reader);

            System.out.println("OBJECT's KEY: " + key);
            while ((c = getc(reader)) != ':' && c != -1) {
                if (!Character.isWhitespace((char) c)) {
                    throw new JsonObjectParserException("Expected colon after key");
                }
            }

            JsonPrimitive<?> value = (JsonPrimitive<?>) getParser(getc(reader)).parse(reader);
            properties.put(key, value);
        }

        return new JsonObject(properties);
    }
}
