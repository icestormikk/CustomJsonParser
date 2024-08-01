package domain.classes;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Car {
    public String brand;
    public List<CarModel> models;
}
