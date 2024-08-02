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

    /**
     * Является ли этот объект примитивным json-типом (строка, число, булевое значение, null)
     * @return true, если объект принадлежит одному из четырёх вышеуказанных типов, иначе false
     */
    public boolean isPrimitive() {
        return !isStruct();
    }

    /**
     * Является ли этот объект структурным json-типом (масссив, объект)
     * @return true, если условие выше верно, иначе false
     */
    public boolean isStruct() {
        return this instanceof JsonArray || this instanceof JsonObject;
    }
}
