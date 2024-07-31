package domain.classes.json;

import domain.abstraction.JsonPrimitive;

public class JsonBoolean extends JsonPrimitive<Boolean> {
    public JsonBoolean(final boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
