package zierke.agnieszka.persistence.validator;

import zierke.agnieszka.persistence.model.Car;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CarValidator implements Validator<Car>{
    @Override
    public Map<String, String> validate(Car car) {
        Map<String, String> errors = new HashMap<>();

        if (car == null){
            errors.put("car", "object is null");
            return errors;
        }
        if(car.getModel() == null){
            errors.put("model", "is null");
        }
        if(!car.getModel().matches("([A-Z]+\\s)*[A-Z]+")){
            errors.put("car model", "doesn't match regex");
        }
        if(car.getColor() == null){
            errors.put("color", "is null");
        }
//        if (car.getColor().equals(Color.BLACK) || car.getColor().equals(Color.WHITE)){
//            errors.put("car color", "is incorrect");
//        }
        if (car.getMileage() < 0){
            errors.put("mileage", "is negative");
        }
        if (car.getPrice().compareTo(BigDecimal.ZERO) < 0){
            errors.put("price", "is negative");
        }
        if (car.getComponents() == null){
            errors.put("components", "are null");
        }
        if (car
                .getComponents()
                .stream()
                .anyMatch(Objects::isNull)){
            errors.put("component", "is null");
        }
        if (car
                .getComponents()
                .stream()
                .anyMatch(component -> !component.matches("([A-Z]+\\s)*[A-Z]+"))){
            errors.put("car component", "doesn't match regex");
        }
        return errors;
    }
}

