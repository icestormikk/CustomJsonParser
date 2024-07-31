package domain.abstraction;

import lombok.Getter;
import lombok.Setter;

/**
 * Общий класс для всех составляющих Json-файла
 * @param <T> Тип значения, которое представляет данный фрагмент файла
 */
@Getter
@Setter
public abstract class JsonPrimitive<T> {
    protected T value;
}
