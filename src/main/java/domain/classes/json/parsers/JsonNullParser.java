package domain.classes.json.parsers;

import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonNullParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonNull;

import java.io.IOException;
import java.io.PushbackReader;

/**
 * Класс для парсинга null-значений, встречающихся в json-файле
 */
public class JsonNullParser extends JsonElementParser<JsonNull> {
    /**
     * Начальный символ (символ, который идентифицирует данные в файле)
     */
    private final int startSymbol;

    public JsonNullParser(final int startSymbol) {
        this.startSymbol = startSymbol;
    }

    /**
     * Метод для парсинга null значения
     * @param reader Объект типа PushbackReader для посимвольного считывания содержимого файла
     * @return null, в случае успешного парсинга
     * @throws JsonParserException Ошибка, возникающая во время обработки json-даннных
     */
    @Override
    public JsonNull parse(final PushbackReader reader) throws JsonParserException {
        StringBuilder builder = new StringBuilder(String.valueOf((char)this.startSymbol));

        try {
            int c;
            while ((c = getc(reader)) != -1) {
                if (c == ',' || c == ']' || c == '}') {
                    reader.unread(c);
                    break;
                }

                builder.append((char)c);
            }
        } catch (IOException e) {
            throw new JsonNullParserException(e.getMessage());
        }

        String value = builder.toString();
        if (value.equals("null")) {
            return new JsonNull();
        }

        throw new JsonNullParserException("Unexpected value (expected: null): " + value);
    }
}
