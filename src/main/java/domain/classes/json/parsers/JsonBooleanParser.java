package domain.classes.json.parsers;

import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonBooleanParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonBoolean;

import java.io.IOException;
import java.io.PushbackReader;

/**
 * Класс для обработки булевых значений в json-файле
 */
public class JsonBooleanParser extends JsonElementParser<JsonBoolean> {
    /**
     * Начальный символ (символ, который идентифицирует данные в файле)
     */
    private final int startSymbol;

    public JsonBooleanParser(final int startSymbol) {
        this.startSymbol = startSymbol;
    }

    /**
     * Метод для парсинга логических значений, указанных в файле
     * @param reader Объект типа PushbackReader для посимвольного считывания содержимого файла
     * @return true, если фрагмент является строкой "true", иначе  false
     * @throws JsonParserException Ошибка, возникающая во время обработки json-даннных
     */
    @Override
    public JsonBoolean parse(final PushbackReader reader) throws JsonParserException {
        StringBuilder builder = new StringBuilder(String.valueOf((char)this.startSymbol));

        try {
            int c;
            while ((c = getc(reader)) != -1) {
                if (c == ',' || c == '}' || c == ']') {
                    reader.unread(c);
                    break;
                }

                builder.append((char)c);
            }
        } catch (IOException e) {
            throw new JsonBooleanParserException(e.getMessage());
        }

        String value = builder.toString();
        switch (value) {
            case "true" -> {
                return new JsonBoolean(true);
            }
            case "false" -> {
                return new JsonBoolean(false);
            }
            default -> throw new JsonBooleanParserException("Unexpected value (expected: boolean): " + value);
        }
    }
}
