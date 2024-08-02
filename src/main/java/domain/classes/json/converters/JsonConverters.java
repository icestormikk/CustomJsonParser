package domain.classes.json.converters;

import domain.classes.Car;
import domain.classes.CarModel;
import domain.classes.ModelTrim;
import domain.classes.json.JsonArray;
import domain.classes.json.JsonObject;
import domain.classes.json.JsonString;

import java.util.ArrayList;
import java.util.List;

/**
 * Набор функций для преобразований структур, состоящих их классов, описывающих json-структуру считанного
 * документа, в список каких-либо объектов
 */
public class JsonConverters {
    /**
     * Преобразование JsonObject в Car
     * @param object объект класса JsonObject, который необходимо преобразовать
     * @return объект класса Car
     */
    public static Car jsonToCar(JsonObject object) {
        JsonString brand = (JsonString) object.getValue().get("brand");
        JsonArray models = (JsonArray) object.getValue().get("models");

        List<CarModel> carModels = new ArrayList<>();
        for (var model : models.getValue()) {
            CarModel carModel = jsonToCarModel((JsonObject) model);
            carModels.add(carModel);
        }

        return new Car(brand.getValue(), carModels);
    }

    /**
     * Преобразование JsonObject в CarModel
     * @param object объект класса JsonObject, который необходимо преобразовать
     * @return объект класса CarModel
     */
    public static CarModel jsonToCarModel(JsonObject object) {
        JsonString name = (JsonString) object.getValue().get("name");
        JsonArray trims = (JsonArray) object.getValue().get("trims");

        List<ModelTrim> modelTrims = new ArrayList<>();
        for (var trim : trims.getValue()) {
            ModelTrim modelTrim = jsonToModelTrim((JsonObject) trim);
            modelTrims.add(modelTrim);
        }

        return new CarModel(name.getValue(), modelTrims);
    }

    /**
     * Преобразование JsonObject в ModelTrim
     * @param object объект класса JsonObject, который необходимо преобразовать
     * @return объект класса ModelTrim
     */
    public static ModelTrim jsonToModelTrim(JsonObject object) {
        JsonString name = (JsonString) object.getValue().get("name");
        return new ModelTrim(name.getValue());
    }
}
