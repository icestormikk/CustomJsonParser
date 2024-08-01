package domain.classes.json;

import domain.abstraction.JsonElement;

public class JsonNull extends JsonElement<Void> {
    public JsonNull() {
        this.value = null;
    }

    @Override
    public String toString() {
        return "null";
    }
}
