package domain.abstraction;

/**
 * Общий класс для всех составляющих Json-файла
 * @param <T> Тип значения, которое представляет данный фрагмент файла
 */
public abstract class JsonElement<T> {
    public abstract T getValue();
}
