import domain.abstraction.JsonElement;
import domain.classes.Car;
import domain.classes.exceptions.JsonParserException;
import domain.classes.json.JsonArray;
import domain.classes.json.JsonObject;
import domain.classes.json.converters.JsonConverters;
import domain.classes.json.parsers.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JsonToCarsParser {
    public static List<Car> parse(String filename) {
        JsonParser parser = new JsonParser(filename);

        JsonElement<?> element;
        try {
            element = parser.parse();
        } catch (JsonParserException e) {
            System.err.println("Во время парсинга произошла ошибка: " + e.getMessage());
            return null;
        }

        List<Car> cars = new ArrayList<>();
        if (element instanceof JsonArray jsonCars) {
            for (JsonElement<?> jsonCar : jsonCars.getValue()) {
                Car car = JsonConverters.jsonToCar((JsonObject) jsonCar);
                cars.add(car);
            }
        }

        return cars;
    }
}
