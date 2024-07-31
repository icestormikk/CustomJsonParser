package domain.classes.json;

import domain.abstraction.JsonPrimitive;

public class JsonNull extends JsonPrimitive<Void> {
    public JsonNull() {
        this.value = null;
    }

    @Override
    public String toString() {
        return "null";
    }
}
