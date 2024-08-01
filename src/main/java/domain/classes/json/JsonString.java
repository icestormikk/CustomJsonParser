package domain.classes.json;

import domain.abstraction.JsonElement;

public class JsonString extends JsonElement<String> {
    public JsonString(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", this.value);
    }
}
