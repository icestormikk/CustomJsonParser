package domain.abstraction;

import domain.classes.exceptions.JsonObjectParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.parsers.*;
import domain.interfaces.CanReadBuffer;

import java.io.PushbackReader;

public abstract class JsonPrimitiveParser<T> implements CanReadBuffer {
    public abstract T parse(final PushbackReader reader) throws JsonParserException;

    public static JsonPrimitiveParser<?> getParser(int character) throws JsonParserException {
        switch (character) {
            case '"' -> {
                return new JsonStringParser();
            }
            case '{' -> {
                return new JsonObjectParser();
            }
            case '[' -> {
                return new JsonArrayParser();
            }
            case 't', 'f' -> {
                return new JsonBooleanParser(character);
            }
            case 'n' -> {
                return new JsonNullParser(character);
            }
            default -> {
                if (Character.isDigit(character) || character == '-') {
                    return new JsonNumberParser(character);
                }
                throw new JsonObjectParserException("Unknown character in value: " + (char)character);
            }
        }
    }
}
