package domain.classes.json;

import domain.abstraction.JsonElement;

/**
 * Класс для представления булевого значения, считанного из json-файла, внутри программы
 */
public class JsonBoolean extends JsonElement<Boolean> {
    /**
     * Основной конструктор
     * @param value булевое значение, которое будет содержать данный объект
     */
    public JsonBoolean(final boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
