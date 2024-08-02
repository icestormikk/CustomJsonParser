package domain.classes.json;

import domain.abstraction.JsonElement;

/**
 * Класс для представления json-строки внутри программы
 */
public class JsonString extends JsonElement<String> {
    /**
     * Основной конструктор
     * @param value Значение, которое будет содержать данный объект
     */
    public JsonString(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("\"%s\"", this.value);
    }
}
