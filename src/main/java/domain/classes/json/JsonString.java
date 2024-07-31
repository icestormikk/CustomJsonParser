package domain.classes.json;

import domain.abstraction.JsonPrimitive;

public class JsonString extends JsonPrimitive<String> {
    public JsonString(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", this.value);
    }
}
