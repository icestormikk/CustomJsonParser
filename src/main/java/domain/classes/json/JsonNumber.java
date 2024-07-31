package domain.classes.json;

import domain.abstraction.JsonPrimitive;

public class JsonNumber extends JsonPrimitive<Number> {
    public JsonNumber(final Number number) {
        this.value = number;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
