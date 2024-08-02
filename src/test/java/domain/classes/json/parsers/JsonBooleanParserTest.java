package domain.classes.json.parsers;

import domain.classes.exceptions.JsonBooleanParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonBoolean;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonBooleanParserTest {

    @Test
    void testParseTrue() throws JsonParserException {
        String json = "true".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonBooleanParser parser = new JsonBooleanParser('t');

        JsonBoolean result = parser.parse(reader);
        assertTrue(result.getValue(), "Expected true value");
    }

    @Test
    void testParseFalse() throws JsonParserException {
        String json = "false".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonBooleanParser parser = new JsonBooleanParser('f');

        JsonBoolean result = parser.parse(reader);
        assertFalse(result.getValue(), "Expected false value");
    }

    @Test
    void testParseInvalidBoolean() {
        String json = "notABoolean".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonBooleanParser parser = new JsonBooleanParser('n');

        assertThrows(JsonBooleanParserException.class, () -> parser.parse(reader), "Expected JsonBooleanParserException");
    }

    @Test
    void testParseWithExtraCharacters() throws JsonParserException {
        String json = "true,extra".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonBooleanParser parser = new JsonBooleanParser('t');

        JsonBoolean result = parser.parse(reader);
        assertTrue(result.getValue(), "Expected true value");

        int nextChar = 0;
        try {
            nextChar = reader.read();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            assert false;
        }

        assertEquals(',', nextChar, "Expected next character to be ','");
    }

}