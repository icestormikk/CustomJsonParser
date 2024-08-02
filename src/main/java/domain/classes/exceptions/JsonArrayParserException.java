package domain.classes.exceptions;

/**
 * Исключение для обозначения ошибок, возникших во время парсинга json-массива
 */
public class JsonArrayParserException extends JsonParserException {
    public JsonArrayParserException(String message) {
        super(message);
    }
}
