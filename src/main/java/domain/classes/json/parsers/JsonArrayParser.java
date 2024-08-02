package domain.classes.json.parsers;

import domain.abstraction.JsonElement;
import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonArrayParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonArray;

import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Парсер json-массивов в файле
 */
public class JsonArrayParser extends JsonElementParser<JsonArray> {
    /**
     * Метод для парсинга json-массивов, указанных в файле
     * @param reader Объект типа PushbackReader для посимвольного считывания содержимого файла
     * @return Массив объектов с общим типом JsonElement<?>
     * @throws JsonParserException Ошибка, возникающая во время обработки json-даннных
     */
    @Override
    public JsonArray parse(PushbackReader reader) throws JsonParserException {
        List<JsonElement<?>> items = new ArrayList<>();

        int c;
        while ((c = getc(reader)) != -1) {
            if (c == ']') {
                return new JsonArray(items);
            }

            items.add((JsonElement<?>) getParser(c).parse(reader));

            while ((c = getc(reader)) != ',' && c != ']' && c != -1) {
                if (!Character.isWhitespace((char) c)) {
                    throw new JsonArrayParserException("Expected comma or closing bracket in array");
                }
            }

            if (c == ']') {
                return new JsonArray(items);
            }
        }

        throw new JsonArrayParserException("Unexpected end of json array");
    }
}
