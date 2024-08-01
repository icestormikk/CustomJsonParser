package domain.classes.json.parsers;

import domain.abstraction.JsonElement;
import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonParserException;
import domain.interfaces.CanReadBuffer;

import java.io.*;

/**
 * Основный класс парсера json-объектов
 */
public class JsonParser implements CanReadBuffer {
    private final String filename;

    public JsonParser(String filename) {
        this.filename = filename;
    }

    /**
     * Распарсить файл, путь к которому был передан в конструкторе данного объекта JsonElementParser
     */
    public JsonElement<?> parse() throws JsonParserException {
        JsonElement<?> result = null;

        try (
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);
            PushbackReader pushbackReader = new PushbackReader(buffer);
        ) {
            int c;
            while ((c = getc(pushbackReader)) != -1) {
                result = (JsonElement<?>) JsonElementParser.getParser(c).parse(pushbackReader);
            }
        } catch (IOException | JsonParserException e) {
            throw new JsonParserException(e.getMessage());
        }

        return result;
    }
}
