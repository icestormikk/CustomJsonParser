package domain.classes;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Класс для хранения информации об автомобилях
 */
@AllArgsConstructor
public class Car {
    /**
     * Марка автомобиля
     */
    public String brand;
    /**
     * Список доступных моделей автомобиля
     */
    public List<CarModel> models;
}
