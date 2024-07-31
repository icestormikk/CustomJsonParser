package domain.classes.json;

import domain.abstraction.JsonPrimitive;

import java.util.Map;
import java.util.stream.Collectors;

public class JsonObject extends JsonPrimitive<Map<JsonString, JsonPrimitive<?>>> {
    public JsonObject(final Map<JsonString, JsonPrimitive<?>> properties) {
        this.value = properties;
    }

    @Override
    public String toString() {
        String props = this.value
                .entrySet()
                .parallelStream()
                .map((entry) -> entry.getKey().toString() + ": " + entry.getValue().toString())
                .collect(Collectors.joining(", "));
        return "{" + props + "}";
    }
}
