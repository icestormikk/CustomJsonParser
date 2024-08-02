package domain.classes.json.parsers;

import domain.abstraction.JsonElement;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @TempDir
    File tempDir;

    @Test
    void parseSimpleObject() throws JsonParserException, IOException {
        File tempFile = new File(tempDir, "simpleObject.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("{\"key\":\"value\"}");
        }

        JsonParser parser = new JsonParser(tempFile.getAbsolutePath());
        JsonElement<?> result = parser.parse();

        assertNotNull(result);
        assertInstanceOf(JsonObject.class, result);
        JsonObject jsonObject = (JsonObject) result;
        assertEquals("value", ((JsonString) jsonObject.getValue().get("key")).getValue());
    }

    @Test
    void parseEmptyArray() throws JsonParserException, IOException {
        File tempFile = new File(tempDir, "emptyArray.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("[]");
        }

        JsonParser parser = new JsonParser(tempFile.getAbsolutePath());
        JsonElement<?> result = parser.parse();

        assertNotNull(result);
        assertInstanceOf(JsonArray.class, result);
        JsonArray jsonArray = (JsonArray) result;
        assertTrue(jsonArray.getValue().isEmpty());
    }

    @Test
    void parseBooleanTrue() throws JsonParserException, IOException {
        File tempFile = new File(tempDir, "booleanTrue.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("true");
        }

        JsonParser parser = new JsonParser(tempFile.getAbsolutePath());
        JsonElement<?> result = parser.parse();

        assertNotNull(result);
        assertInstanceOf(JsonBoolean.class, result);
        assertTrue(((JsonBoolean) result).getValue());
    }

    @Test
    void parseBooleanFalse() throws JsonParserException, IOException {
        File tempFile = new File(tempDir, "booleanFalse.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("false");
        }

        JsonParser parser = new JsonParser(tempFile.getAbsolutePath());
        JsonElement<?> result = parser.parse();

        assertNotNull(result);
        assertInstanceOf(JsonBoolean.class, result);
        assertFalse(((JsonBoolean) result).getValue());
    }

    @Test
    void parseNullValue() throws JsonParserException, IOException {
        File tempFile = new File(tempDir, "nullValue.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("null");
        }

        JsonParser parser = new JsonParser(tempFile.getAbsolutePath());
        JsonElement<?> result = parser.parse();

        assertNotNull(result);
        assertInstanceOf(JsonNull.class, result);
    }

    @Test
    void parseNumber() throws JsonParserException, IOException {
        File tempFile = new File(tempDir, "number.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("27022006");
        }

        JsonParser parser = new JsonParser(tempFile.getAbsolutePath());
        JsonElement<?> result = parser.parse();

        assertNotNull(result);
        assertInstanceOf(JsonNumber.class, result);
        assertEquals(27022006L, ((JsonNumber) result).getValue());
    }

    @Test
    void parseInvalidJson() throws IOException {
        File tempFile = new File(tempDir, "invalidJson.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("{invalid:json}");
        }

        JsonParser parser = new JsonParser(tempFile.getAbsolutePath());

        assertThrows(JsonParserException.class, parser::parse);
    }
}