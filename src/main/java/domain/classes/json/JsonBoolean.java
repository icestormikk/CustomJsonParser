package domain.classes.json;

import domain.abstraction.JsonElement;

public class JsonBoolean extends JsonElement<Boolean> {
    public JsonBoolean(final boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
