package domain.abstraction;

import domain.classes.json.JsonArray;
import domain.classes.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

/**
 * Общий класс для всех составляющих Json-файла
 * @param <T> Тип значения, которое представляет данный фрагмент файла
 */
@Getter
@Setter
public abstract class JsonElement<T> {
    protected T value;

    public boolean isPrimitive() {
        return !isStruct();
    }

    public boolean isStruct() {
        return this instanceof JsonArray || this instanceof JsonObject;
    }
}
