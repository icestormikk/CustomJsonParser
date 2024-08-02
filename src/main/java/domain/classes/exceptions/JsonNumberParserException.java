package domain.classes.exceptions;

/**
 * Исключение для обозначения ошибок, возникших во время парсинга числового значения
 */
public class JsonNumberParserException extends JsonParserException {
    public JsonNumberParserException(String message) {
        super(message);
    }
}
