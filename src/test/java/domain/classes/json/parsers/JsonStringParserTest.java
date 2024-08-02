package domain.classes.json.parsers;

import domain.classes.exceptions.JsonParserException;
import domain.classes.exceptions.JsonStringParserException;
import domain.classes.json.JsonString;
import org.junit.jupiter.api.Test;

import java.io.PushbackReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonStringParserTest {

    @Test
    void parseSimpleString() throws JsonParserException {
        String json = "\"simple string\"".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonStringParser parser = new JsonStringParser();

        JsonString result = parser.parse(reader);
        assertEquals("simple string", result.getValue(), "Expected value 'simple string'");
    }

    @Test
    void parseEmptyString() throws JsonParserException {
        String json = "\"\"".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonStringParser parser = new JsonStringParser();

        JsonString result = parser.parse(reader);
        assertEquals("", result.getValue(), "Expected empty string");
    }

    @Test
    void parseStringWithWhitespace() throws JsonParserException {
        String json = "\"  with spaces  \"".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonStringParser parser = new JsonStringParser();

        JsonString result = parser.parse(reader);
        assertEquals("  with spaces  ", result.getValue(), "Expected value '  with spaces  '");
    }

    @Test
    void parseStringWithSpecialCharacters() throws JsonParserException {
        String json = "\"special!@#$%^&*()\"".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonStringParser parser = new JsonStringParser();

        JsonString result = parser.parse(reader);
        assertEquals("special!@#$%^&*()", result.getValue(), "Expected value 'special!@#$%^&*()'");
    }

    @Test
    void parseUnexpectedEndOfString() {
        String json = "\"unterminated".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonStringParser parser = new JsonStringParser();
        assertThrows(JsonStringParserException.class, () -> parser.parse(reader), "Expected JsonStringParserException for unterminated string");
    }
}