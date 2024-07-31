package domain.classes.json;

import domain.abstraction.JsonPrimitive;

import java.util.List;
import java.util.stream.Collectors;

public class JsonArray extends JsonPrimitive<List<JsonPrimitive<?>>> {
    public JsonArray(List<JsonPrimitive<?>> items) {
        this.value = items;
    }

    @Override
    public String toString() {
        return "[" + this.value.stream().map(JsonPrimitive::toString).collect(Collectors.joining(", ")) + "]";
    }
}
