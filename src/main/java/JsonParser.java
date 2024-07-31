import domain.abstraction.JsonPrimitive;
import domain.abstraction.JsonPrimitiveParser;
import domain.classes.exceptions.JsonObjectParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.exceptions.JsonStringParserException;
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
     * Распарсить файл, путь к которому был передан в конструкторе данного объекта JsonPrimitiveParser
     */
    public JsonPrimitive<?> parse() {
        JsonPrimitive<?> result = null;

        try (
            InputStream inputStream = new FileInputStream(filename);
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(reader);
            PushbackReader pushbackReader = new PushbackReader(buffer);
        ) {
            int c;
            while ((c = getc(pushbackReader)) != -1) {
                result = (JsonPrimitive<?>) JsonPrimitiveParser.getParser(c).parse(pushbackReader);
            }
        } catch (IOException | JsonParserException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
