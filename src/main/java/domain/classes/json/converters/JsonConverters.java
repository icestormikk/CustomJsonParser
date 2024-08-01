package domain.classes.json.converters;

import domain.classes.Car;
import domain.classes.CarModel;
import domain.classes.ModelTrim;
import domain.classes.json.JsonArray;
import domain.classes.json.JsonObject;
import domain.classes.json.JsonString;

import java.util.ArrayList;
import java.util.List;

public class JsonConverters {
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

    public static ModelTrim jsonToModelTrim(JsonObject object) {
        JsonString name = (JsonString) object.getValue().get("name");
        return new ModelTrim(name.getValue());
    }
}
