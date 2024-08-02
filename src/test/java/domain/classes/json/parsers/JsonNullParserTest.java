package domain.classes.json.parsers;

import domain.classes.exceptions.JsonNullParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonNull;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonNullParserTest {

    @Test
    void testParseNull() throws JsonParserException {
        String json = "null".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNullParser parser = new JsonNullParser('n');

        JsonNull result = parser.parse(reader);
        assertNotNull(result, "Expected non-null JsonNull object");
    }

    @Test
    void testParseInvalidNull() {
        String json = "notNull".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNullParser parser = new JsonNullParser('n');

        assertThrows(JsonNullParserException.class, () -> parser.parse(reader), "Expected JsonNullParserException");
    }

    @Test
    void testParseNullWithExtraCharacters() throws JsonParserException {
        String json = "null,extra".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonNullParser parser = new JsonNullParser('n');

        JsonNull result = parser.parse(reader);
        assertNotNull(result, "Expected non-null JsonNull object");

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