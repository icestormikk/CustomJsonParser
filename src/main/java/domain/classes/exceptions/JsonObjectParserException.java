package domain.classes.exceptions;

/**
 * Исключение для обозначения ошибок, возникших во время парсинга json-объекта
 */
public class JsonObjectParserException extends JsonParserException {
    public JsonObjectParserException(String message) {
        super(message);
    }
}
