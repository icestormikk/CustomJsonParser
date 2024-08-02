package domain.classes.json;

import domain.abstraction.JsonElement;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для представление json-объектов внутри программы
 */
public class JsonObject extends JsonElement<Map<String, JsonElement<?>>> {
    /**
     * Основной конструктор
     * @param properties свойства данного json-объекта
     */
    public JsonObject(final Map<String, JsonElement<?>> properties) {
        this.value = properties;
    }

    @Override
    public String toString() {
        String props = this.value
                .entrySet()
                .parallelStream()
                .map((entry) -> entry.getKey() + ": " + entry.getValue().toString())
                .collect(Collectors.joining(", "));
        return "{" + props + "}";
    }
}
