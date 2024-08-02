package domain.classes;


import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Модель автомобиля
 */
@AllArgsConstructor
public class CarModel {
    /**
     * Название модели
     */
    public String name;

    /**
     * Доступные комплектации модели
     */
    public List<ModelTrim> trims;
}
