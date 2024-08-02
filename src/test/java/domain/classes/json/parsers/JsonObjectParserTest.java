package domain.classes.json.parsers;

import domain.classes.exceptions.JsonObjectParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonNumber;
import domain.classes.json.JsonObject;
import domain.classes.json.JsonString;
import org.junit.jupiter.api.Test;

import java.io.PushbackReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class JsonObjectParserTest {
    @Test
    void testParseEmptyObject() throws JsonParserException {
        String json = "{}".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonObjectParser parser = new JsonObjectParser();

        JsonObject result = parser.parse(reader);
        assertTrue(result.getValue().isEmpty(), "Expected empty JSON object");
    }

    @Test
    void testParseSimpleObject() throws JsonParserException {
        String json = "{\"key\":\"value\"}".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonObjectParser parser = new JsonObjectParser();

        JsonObject result = parser.parse(reader);
        assertEquals(1, result.getValue().size(), "Expected one property in JSON object");
        assertTrue(result.getValue().containsKey("key"), "Expected key 'key' in JSON object");
        assertEquals("value", ((JsonString)result.getValue().get("key")).getValue(), "Expected value 'value' for key 'key'");
    }

    @Test
    void testParseNestedObject() throws JsonParserException {
        String json = "{\"outer\":{\"inner\":\"value\"}}".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonObjectParser parser = new JsonObjectParser();

        JsonObject result = parser.parse(reader);
        assertEquals(1, result.getValue().size(), "Expected one property in JSON object");
        assertTrue(result.getValue().containsKey("outer"), "Expected key 'outer' in JSON object");

        JsonObject innerObject = (JsonObject) result.getValue().get("outer");
        assertTrue(innerObject.getValue().containsKey("inner"), "Expected key 'inner' in inner JSON object");
        assertEquals("value", ((JsonString)innerObject.getValue().get("inner")).getValue(), "Expected value 'value' for key 'inner'");
    }

    @Test
    void testParseInvalidObject() {
        String json = "{key:\"value\"}".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json));
        JsonObjectParser parser = new JsonObjectParser();

        assertThrows(JsonObjectParserException.class, () -> parser.parse(reader), "Expected JsonObjectParserException for invalid JSON object");
    }

    @Test
    void testParseObjectWithMultipleProperties() throws JsonParserException {
        String json = "{\"key1\":\"value1\", \"key2\":42}".substring(1);

        PushbackReader reader = new PushbackReader(new StringReader(json), 1);
        JsonObjectParser parser = new JsonObjectParser();

        JsonObject result = parser.parse(reader);
        assertEquals(2, result.getValue().size(), "Expected two properties in JSON object");
        assertTrue(result.getValue().containsKey("key1"), "Expected key 'key1' in JSON object");
        assertEquals("value1", ((JsonString)result.getValue().get("key1")).getValue(), "Expected value 'value1' for key 'key1'");
        assertTrue(result.getValue().containsKey("key2"), "Expected key 'key2' in JSON object");
        assertEquals(42L, ((JsonNumber)result.getValue().get("key2")).getValue(), "Expected value 42 for key 'key2'");
    }
}