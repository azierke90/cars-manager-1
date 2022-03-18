package zierke.agnieszka.persistence.converter;

import zierke.agnieszka.persistence.model.Car;

import java.util.List;

public class CarsJsonConverter extends JsonConverter<List<Car>>{
    public CarsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
