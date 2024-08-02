package domain.classes.json.parsers;

import domain.abstraction.JsonElementParser;
import domain.classes.exceptions.JsonNumberParserException;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonNumber;

import java.io.IOException;
import java.io.PushbackReader;
/**
 * Класс для обработки числовых значений в json-файле
 */
public class JsonNumberParser extends JsonElementParser<JsonNumber> {
    /**
     * Начальный символ (символ, который идентифицирует данные в файле)
     */
    private final int startSymbol;

    public JsonNumberParser(int startSymbol) {
        this.startSymbol = startSymbol;
    }

    /**
     * Метод для парсинга числовых значения
     * @param reader Объект типа PushbackReader для посимвольного считывания содержимого файла
     * @return Вещественное либо целое число
     * @throws JsonParserException Ошибка, возникающая во время обработки json-даннных
     */
    @Override
    public JsonNumber parse(final PushbackReader reader) throws JsonParserException {
        StringBuilder builder = new StringBuilder(String.valueOf((char)startSymbol));

        int c;
        try {
            while ((c = getc(reader)) != -1) {
                if (c == ',' || c == '}' || c == ']') {
                    reader.unread(c);
                    break;
                }
                builder.append((char)c);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }


        try {
            String number = builder.toString();
            if (number.contains(".") || number.contains("e") || number.contains("E")) {
                return new JsonNumber(Double.parseDouble(number));
            }
            return new JsonNumber(Long.parseLong(number));
        } catch (NumberFormatException ex) {
            throw new JsonNumberParserException("Error while reading number: " + ex.getMessage());
        }
    }
}
