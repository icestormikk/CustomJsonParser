package domain.classes.json.parsers;

import domain.classes.exceptions.JsonArrayParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.*;
import org.junit.jupiter.api.Test;

import java.io.PushbackReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonArrayParserTest {
    @Test
    public void testParseEmptyArray() throws JsonParserException {
        String json = "[]".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonArrayParser parser = new JsonArrayParser();

        JsonArray result = parser.parse(reader);
        assertTrue(result.getValue().isEmpty(), "Expected empty array");
    }

    @Test
    public void testParseArrayWithPrimitives() throws JsonParserException {
        String json = "[1, true, \"string\"]".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonArrayParser parser = new JsonArrayParser();

        JsonArray result = parser.parse(reader);
        assertEquals(3, result.getValue().size(), "Expected array with 3 elements");
        assertInstanceOf(JsonNumber.class, result.getValue().get(0));
        assertInstanceOf(JsonBoolean.class, result.getValue().get(1));
        assertInstanceOf(JsonString.class, result.getValue().get(2));
    }

    @Test
    public void testParseArrayWithNestedArray() throws JsonParserException {
        String json = "[1, [2, 3], 4]".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonArrayParser parser = new JsonArrayParser();

        JsonArray result = parser.parse(reader);
        assertEquals(3, result.getValue().size(), "Expected array with 3 elements");
        assertInstanceOf(JsonArray.class, result.getValue().get(1));

        JsonArray nestedArray = (JsonArray) result.getValue().get(1);
        assertEquals(2, nestedArray.getValue().size(), "Expected nested array with 2 elements");
    }

    @Test
    public void testParseArrayWithObject() throws JsonParserException {
        String json = "[{\"key\": \"value\"}, 2]".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonArrayParser parser = new JsonArrayParser();

        JsonArray result = parser.parse(reader);
        assertEquals(2, result.getValue().size(), "Expected array with 2 elements");
        assertInstanceOf(JsonObject.class, result.getValue().get(0));
        assertInstanceOf(JsonNumber.class, result.getValue().get(1));
    }

    @Test
    public void testParseArrayWithUnexpectedEnd() {
        String json = "[1, 2".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonArrayParser parser = new JsonArrayParser();

        assertThrows(JsonArrayParserException.class, () -> parser.parse(reader), "Expected JsonArrayParserException");
    }
}