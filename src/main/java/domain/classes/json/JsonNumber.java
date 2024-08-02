package domain.classes.json;

import domain.abstraction.JsonElement;

/**
 * Класс для представления json-числа (целое либо вещественное) внутри программы
 */
public class JsonNumber extends JsonElement<Number> {
    /**
     * Основной конструктор
     * @param number число, которое будет хранить данный объект
     */
    public JsonNumber(final Number number) {
        this.value = number;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
