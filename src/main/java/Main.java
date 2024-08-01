import domain.classes.Car;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = JsonToCarsParser.parse("test_json_file.json");

        if (cars == null) {
            throw new IllegalStateException("Не удалось корректно преобразовать json-структуру в массив объектов Car");
        }

        List<List<String>> result = carsToTwoDimensionalArray(cars);
        result.forEach(System.out::println);
    }

    public static List<List<String>> carsToTwoDimensionalArray(List<Car> cars) {
        List<List<String>> result = new ArrayList<>();
        cars.forEach((car) -> {
            car.models.forEach((model) -> {
                model.trims.forEach((trim) -> {
                    result.add(List.of(car.brand, model.name, trim.name));
                });
            });
        });

        return result;
    }
}
