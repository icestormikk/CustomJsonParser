package domain.classes.json;

import domain.abstraction.JsonElement;

import java.util.List;
import java.util.stream.Collectors;

public class JsonArray extends JsonElement<List<JsonElement<?>>> {
    public JsonArray(List<JsonElement<?>> items) {
        this.value = items;
    }

    @Override
    public String toString() {
        return "[" + this.value.stream().map(JsonElement::toString).collect(Collectors.joining(", ")) + "]";
    }
}
