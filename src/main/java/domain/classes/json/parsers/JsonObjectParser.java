package domain.classes.json.parsers;

import domain.abstraction.JsonElement;
import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonObjectParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonObject;
import domain.classes.json.JsonString;

import java.io.PushbackReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для парсинга всех json-объектов в файле
 */
public class JsonObjectParser extends JsonElementParser<JsonObject> {
    /**
     * Метод для посимвольной конвертации в JsonObject
     * @param reader Объект типа PushbackReader для посимвольного считывания содержимого файла
     * @return объект класса JsonObject в случае успешноо чтения файла
     * @throws JsonParserException Ошибка, возникающая во время обработки json-даннных
     */
    @Override
    public JsonObject parse(final PushbackReader reader) throws JsonParserException {
        Map<String, JsonElement<?>> properties = new HashMap<>();

        int c;
        while ((c = getc(reader)) != -1) {
            if (c == ',') {
                continue;
            }
            if (c == '}') {
                return new JsonObject(properties);
            }
            if (c != '"') {
                throw new JsonObjectParserException("Unexpected character in object: " + (char)c);
            }

            JsonString key = new JsonStringParser().parse(reader);

            while ((c = getc(reader)) != ':' && c != -1) {
                if (!Character.isWhitespace((char) c)) {
                    throw new JsonObjectParserException("Expected colon after key");
                }
            }

            JsonElement<?> value = (JsonElement<?>) getParser(getc(reader)).parse(reader);
            properties.put(key.getValue(), value);
        }

        return new JsonObject(properties);
    }
}
