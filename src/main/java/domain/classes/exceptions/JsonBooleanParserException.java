package domain.classes.exceptions;

/**
 * Исключение для обозначения ошибок, возникших во время парсинга булевого значения
 */
public class JsonBooleanParserException extends JsonParserException {
    public JsonBooleanParserException(String message) {
        super(message);
    }
}
