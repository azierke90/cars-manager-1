package zierke.agnieszka.service;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;
import zierke.agnieszka.persistence.converter.CarsJsonConverter;
import zierke.agnieszka.persistence.model.Car;
import zierke.agnieszka.persistence.model.type.Color;
import zierke.agnieszka.persistence.validator.CarValidator;
import zierke.agnieszka.persistence.validator.Validator;
import zierke.agnieszka.service.exception.CarsServiceException;
import zierke.agnieszka.service.type.Sort;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CarsService {
    private final List<Car> cars;

    public CarsService(String filename) {
        this.cars = init(filename);
    }

    /**
     * The method which takes the filename of a JSON file as an argument that stores data about example cars
     * and creates a collection of Car class objects
     * @param filename
     * @return
     */
    private static List<Car> init(String filename){
        CarValidator carValidator = new CarValidator();

        return new CarsJsonConverter(filename)
                .fromJson()
                .orElseThrow(() -> new CarsServiceException("Cannot read data from json %s%n".formatted(filename)))
                .stream()
                .peek(car -> Validator.validate(car, carValidator))
                .toList();
    }

    /**
     * The method that returns a new collection of Car elements sorted according to the specified criterion method argument.
     * The method should be able to sort by model name, color, price and mileage.
     * Additionally, you should define whether sorting is to be done in descending or ascending way.
     * @param sort
     * @param descending
     * @return
     */
    public List<Car> sort(Sort sort, boolean descending){
        if (sort == null){
            throw new CarsServiceException("sort is null");
        }

        List<Car> sortedCars = new ArrayList<>();

        if (!descending) {
            sortedCars =
                    switch (sort){

                        case MODEL -> cars.stream().sorted(Comparator.comparing(Car::getModel)).toList();
                        case COLOR -> cars.stream().sorted(Comparator.comparing(Car::getColor)).toList();
                        case PRICE -> cars.stream().sorted(Comparator.comparing(Car::getPrice)).toList();
                        case MILEAGE -> cars.stream().sorted(Comparator.comparing(Car::getMileage)).toList();
                    };

        } else {
            sortedCars =
                    switch (sort){
                        case MODEL -> cars.stream().sorted(Comparator.comparing(Car::getModel, Comparator.reverseOrder())).toList();
                        case COLOR -> cars.stream().sorted(Comparator.comparing(Car::getColor, Comparator.reverseOrder())).toList();
                        case PRICE -> cars.stream().sorted(Comparator.comparing(Car::getPrice, Comparator.reverseOrder())).toList();
                        case MILEAGE -> cars.stream().sorted(Comparator.comparing(Car::getMileage, Comparator.reverseOrder())).toList();
                    };

        }
        return sortedCars;
    }

    /**
     * The method returns a collection of Car type elements that have a mileage greater than the value given
     * as an argument of the method.
     * @param mileage
     * @return
     */

    public List<Car> findAllWithMileageGreaterThan(int mileage){
        return cars
                .stream()
                .filter(car -> car.hasMileageGreaterThan(mileage))
                .toList();
    }

    /**
     *The method returns a map where key is a color, and the value is the number of cars that have that color.
     * The map should be sorted in descending order of values.
     * @return
     */

    public Map<Color, Long> descCountByColor(){
        return cars
                .stream()
                .collect(groupingBy(Car::getColor, counting()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    /**
     * The method returns a map where key is the name of the car model, and the value is Car object
     * that represents the most expensive car with this model name.
     * The map should be sorted with the keys in descending order.
     * @return
     */

    //    1. SPOSÓB
    public Map<String, Car> descFindMostExpensiveCarForEachModel() {
        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getModel))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().max(Comparator.comparing(Car::getPrice)).orElseThrow(() -> new CarsServiceException("no car with max price")),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    //    2. SPOSÓB
    public Map<String, Car> descFindMostExpensiveCarForEachModel2(){
        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getModel))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new Car().mostExpensiveCar(e.getValue()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    /**
     * The method prints cars statistics in the list.
     * The statistics include the average value, the lowest value, and the highest value
     * for price and mileage of cars.
     */

    public void carStatistics(){
        mileageStatistics();
        priceStatistics();
    }

    private void mileageStatistics(){
        DoubleSummaryStatistics mileageStatistics = cars
                .stream()
                .collect(Collectors.summarizingDouble(Car::getMileage));

        System.out.println("--------------------------------- Mileage statistics ---------------------------------");
        System.out.println(MessageFormat.format("avg = {0}, min = {1}, max = {2}",
                mileageStatistics.getAverage(), mileageStatistics.getMin(),mileageStatistics.getMax()));
    }

    private void priceStatistics(){
        BigDecimalSummaryStatistics priceStatistics = cars
                .stream()
                .collect(Collectors2.summarizingBigDecimal(Car::getPrice));

        System.out.println("--------------------------------- Price statistics ---------------------------------");
        System.out.println(MessageFormat.format("avg = {0}, min = {1}, max = {2}",
                priceStatistics.getAverage(), priceStatistics.getMin(), priceStatistics.getMax()));
    }

    /**
     * The method returns the car with the highest price.
     * If there is more than one car with the highest price, the collection of these cars will be returned.
     * @return
     */

    public List<Car> mostExpensiveCars(){
        return cars
                .stream()
                .collect(groupingBy(Car::getPrice))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .orElseThrow(() -> new CarsServiceException("no car with max price"))
                .getValue();
    }

    /**
     * Method that returns list of cars where car components are sorted alphabetically.
     * @return
     */

    public List<Car> sortCarComponents(){
        return cars
                .stream()
                .peek(car -> car.setComponents(car.getComponents().stream().sorted().toList()))
                .toList();
    }

    /**
     * Map returns method, where the key is the car component and the value is the collection of cars that have that component.
     * Map is sorted in descending order by the number of items in the collection for the pair value.
     * @return
     */

    public Map<String, List<Car>> descGroupByComponents() {
        return cars
                .stream()
                .flatMap(car -> car.getComponents().stream())
                .toList()
                .stream()
                .distinct()
                .collect(Collectors.toMap(
                        comp -> comp,
                        comp -> cars.stream().filter(car -> car.getComponents().contains(comp)).collect(toList())
                ))
                .entrySet()
                .stream()
                .sorted((c1, c2) -> Integer.compare(c2.getValue().size(), c1.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    public List<Car> findCarsInPriceRange(int a, int b){
        if (a > b){
            throw new CarsServiceException("a is bigger than b, so price range is incorrect");
        }
        return cars
                .stream()
                .filter(car -> car.getPrice().compareTo(BigDecimal.valueOf(a)) >= 0 && car.getPrice().compareTo(BigDecimal.valueOf(b)) <= 0)
                .sorted(Comparator.comparing(Car::getModel))
                .toList();
    }

    /**
     * The toString method overridden, which will return a string showing the data of all cars from the collection in a transparent format.
     * @return
     */

    @Override
    public String toString() {
        return "CarService{" +
                "cars=" + cars +
                '}';
    }
}

