package domain.abstraction;

import domain.classes.exceptions.JsonParserException;
import domain.classes.json.parsers.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonElementParserTest {
    @Test
    void stringParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('"');
        assertInstanceOf(JsonStringParser.class, parser, "Expected string parser");
    }

    @Test
    void objectParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('{');
        assertInstanceOf(JsonObjectParser.class, parser, "Expected object parser");
    }

    @Test
    void arrayParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('[');
        assertInstanceOf(JsonArrayParser.class, parser, "Expected array parser");
    }

    @Test
    void booleanTrueParser() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('t');
        assertInstanceOf(JsonBooleanParser.class, parser, "Expected boolean (true) parser");
    }

    @Test
    void booleanFalseParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('f');
        assertInstanceOf(JsonBooleanParser.class, parser, "Expected boolean (false) parser");
    }

    @Test
    void nullParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('n');
        assertInstanceOf(JsonNullParser.class, parser, "Expected parser for null value");
    }

    @Test
    void positiveNumberParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('1');
        assertInstanceOf(JsonNumberParser.class, parser, "Expected number parser");
    }

    @Test
    void negativeNumberParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('-');
        assertInstanceOf(JsonNumberParser.class, parser, "Expected number parser");
    }


    @Test
    void zeroNumberParserTest() throws JsonParserException {
        JsonElementParser<?> parser = JsonElementParser.getParser('0');
        assertInstanceOf(JsonNumberParser.class, parser, "Expected number parser");
    }

    @Test
    void throwJsonParserException() {
        var exception = assertThrows(JsonParserException.class, () -> {
            JsonElementParser.getParser('%');
        });
        assertTrue(exception.getMessage().contains("Unknown character in value"));
    }
}