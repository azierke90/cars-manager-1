package zierke.agnieszka.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zierke.agnieszka.persistence.model.exception.CarModelException;
import zierke.agnieszka.persistence.model.type.Color;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Car {
    private String model;
    private BigDecimal price;
    private Color color;
    private double mileage;
    private List<String> components;

    public boolean hasMileageGreaterThan(int limit){
        if (limit <= 0){
            throw new CarModelException("limit value must be positive");
        }
        return mileage > limit;
    }

    public Car mostExpensiveCar(List<Car> cars){
        return cars
                .stream()
                .max(Comparator.comparing(Car::getPrice))
                .orElseThrow(() -> new CarModelException("no car with max price"));
    }
}
