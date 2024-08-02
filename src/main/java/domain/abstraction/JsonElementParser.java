package domain.abstraction;

import domain.classes.exceptions.JsonParserException;
import domain.classes.json.parsers.*;
import domain.interfaces.CanReadBuffer;

import java.io.PushbackReader;

/**
 * Общий класс для всех парсеров внутри программы
 * @param <T> Тип данных, которые содержит элемент для парсинга
 */
public abstract class JsonElementParser<T> implements CanReadBuffer {
    /**
     * Функция для посимвольного парсинга определённого фрагмента файла
     * @param reader Объект типа PushbackReader для посимвольного считывания содержимого файла
     * @return Объект для работы с json-данными внутри программы
     * @throws JsonParserException Ошибка, возникшая во время парсинга файла
     */
    public abstract T parse(final PushbackReader reader) throws JsonParserException;

    /**
     * Получение парсера в зависимости от текущего символва
     * @param character символ, который определяет тип получаемого парсера
     * @return парсер, который необходим (или наиболее подходит) для обработки значения
     * @throws JsonParserException Ошибка, возникшая во время парсинга файла
     */
    public static JsonElementParser<?> getParser(int character) throws JsonParserException {
        switch (character) {
            case '"' -> {
                return new JsonStringParser();
            }
            case '{' -> {
                return new JsonObjectParser();
            }
            case '[' -> {
                return new JsonArrayParser();
            }
            case 't', 'f' -> {
                return new JsonBooleanParser(character);
            }
            case 'n' -> {
                return new JsonNullParser(character);
            }
            default -> {
                if (Character.isDigit(character) || character == '-') {
                    return new JsonNumberParser(character);
                }
                throw new JsonParserException("Unknown character in value: " + (char)character);
            }
        }
    }
}
