package domain.classes;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CarModel {
    public String name;
    public List<ModelTrim> trims;
}
