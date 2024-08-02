package domain.classes.json;

import domain.abstraction.JsonElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для представления json-массива внутри программы
 */
public class JsonArray extends JsonElement<List<JsonElement<?>>> {
    /**
     * Основной конструктор
     * @param items список объектов, которые содержит данный массив
     */
    public JsonArray(List<JsonElement<?>> items) {
        this.value = items;
    }

    @Override
    public String toString() {
        return "[" + this.value.stream().map(JsonElement::toString).collect(Collectors.joining(", ")) + "]";
    }
}
