package domain.classes.exceptions;

/**
 * Исключение для обозначения ошибок, возникших во время парсинга null значения
 */
public class JsonNullParserException extends JsonParserException {
    public JsonNullParserException(String message) {
        super(message);
    }
}
