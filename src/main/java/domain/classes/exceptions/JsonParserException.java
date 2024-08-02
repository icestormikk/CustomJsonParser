package domain.classes.exceptions;

/**
 * Общий класс для всех типов исключений, связанных с парсингом json-значений
 */
public class JsonParserException extends Throwable {
    public JsonParserException(String message) {
        super(message);
    }
}
