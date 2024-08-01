package domain.classes.json;

import domain.abstraction.JsonElement;

public class JsonNumber extends JsonElement<Number> {
    public JsonNumber(final Number number) {
        this.value = number;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
