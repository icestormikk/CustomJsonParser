package domain.classes.exceptions;

/**
 * Исключение для обозначения ошибок, возникших во время парсинга строки
 */
public class JsonStringParserException extends JsonParserException {
    public JsonStringParserException(String message) {
        super(message);
    }
}
