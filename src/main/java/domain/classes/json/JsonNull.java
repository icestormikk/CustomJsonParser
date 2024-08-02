package domain.classes.json;

import domain.abstraction.JsonElement;

/**
 * Класс для представления null значения, считанного из json-файла, внутри программы
 */
public class JsonNull extends JsonElement<Void> {
    /**
     * Основной конструктор
     */
    public JsonNull() {
        this.value = null;
    }

    @Override
    public String toString() {
        return "null";
    }
}
