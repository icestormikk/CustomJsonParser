package domain.classes.json.parsers;

import domain.classes.exceptions.JsonNumberParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonNumber;
import org.junit.jupiter.api.Test;

import java.io.PushbackReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonNumberParserTest {

    @Test
    void testParseInteger() throws JsonParserException {
        String json = "42".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNumberParser parser = new JsonNumberParser('4');

        JsonNumber result = parser.parse(reader);
        assertEquals(42, result.getValue().longValue(), "Expected integer value 42");
    }

    @Test
    void testParseNegativeInteger() throws JsonParserException {
        String json = "-42".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNumberParser parser = new JsonNumberParser('-');

        JsonNumber result = parser.parse(reader);
        assertEquals(-42L, result.getValue(), "Expected negative integer value -42");
    }

    @Test
    void testParseDouble() throws JsonParserException {
        String json = "42.24".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNumberParser parser = new JsonNumberParser('4');

        JsonNumber result = parser.parse(reader);
        assertEquals(42.24, result.getValue().doubleValue(), 0.00001, "Expected double value 42.24");
    }

    @Test
    void testParseNegativeDouble() throws JsonParserException {
        String json = "-42.24".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNumberParser parser = new JsonNumberParser('-');

        JsonNumber result = parser.parse(reader);
        assertEquals(-42.24, result.getValue().doubleValue(), 0.00001, "Expected negative double value -42.24");
    }

    @Test
    void testParseScientificNotation() throws JsonParserException {
        String json = "1.23e4".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNumberParser parser = new JsonNumberParser('1');

        JsonNumber result = parser.parse(reader);
        assertEquals(1.23e4, result.getValue().doubleValue(), 0.00001, "Expected scientific notation value 1.23e4");
    }

    @Test
    void testParseInvalidNumber() {
        String json = "abc".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));

        JsonNumberParser parser = new JsonNumberParser('a');
        assertThrows(JsonNumberParserException.class, () -> parser.parse(reader), "Expected JsonNumberParserException");
    }
}