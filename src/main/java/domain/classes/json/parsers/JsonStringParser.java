package domain.classes.json.parsers;

import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonParserException;
import domain.classes.exceptions.JsonStringParserException;
import domain.classes.json.JsonString;

import java.io.IOException;
import java.io.PushbackReader;

/**
 * Класс для парсинга строковых значений, встречающихся в json-файле
 */
public class JsonStringParser extends JsonElementParser<JsonString> {
    /**
     * Преобразование строкого значения в json-файле в строковое значение внутри файла
     * @param reader Объект типа PushbackReader для посимвольного считывания содержимого файла
     * @return объект класса JsonObject в случае успешного парсинга
     * @throws JsonParserException Ошибка, возникающая во время обработки json-даннных
     */
    @Override
    public JsonString parse(final PushbackReader reader) throws JsonParserException {
        StringBuilder builder = new StringBuilder();

        try {
            int c;
            while ((c = reader.read()) != -1) {
                if (c == '"') {
                    return new JsonString(builder.toString());
                }

                builder.append((char)c);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        throw new JsonStringParserException("Unexpected end of string");
    }
}
