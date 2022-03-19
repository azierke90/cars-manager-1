package zierke.agnieszka.api;

import zierke.agnieszka.service.CarsService;
import zierke.agnieszka.service.type.Sort;

public class App {

    public static void main(String[] args) {

        final var FILENAME = "./data/cars.json";
        var carsService = new CarsService(FILENAME);

        System.out.println("--------------------------------- 1 - SORT ---------------------------------");
        System.out.println("Cars sorted by model in descending order");
        var descModelSortedCars = carsService.sort(Sort.MODEL, true);
        descModelSortedCars.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------");
        System.out.println("Cars sorted by model in ascending order");
        var ascModelSortedCars = carsService.sort(Sort.MODEL, false);
        ascModelSortedCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Cars sorted by color in descending order");
        var descColorSortedCars = carsService.sort(Sort.COLOR, true);
        descColorSortedCars.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------");
        System.out.println("Cars sorted by color in ascending order");
        var ascColorSortedCars = carsService.sort(Sort.COLOR, false);
        ascColorSortedCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Cars sorted by price in descending order");
        var descPriceSortedCars = carsService.sort(Sort.PRICE, true);
        descPriceSortedCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Cars sorted by price in ascending order");
        var ascPriceSortedCars = carsService.sort(Sort.PRICE, false);
        ascPriceSortedCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Cars sorted by mileage in descending order");
        var descMileageSortedCars = carsService.sort(Sort.MILEAGE, true);
        descMileageSortedCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("Cars sorted by mileage in ascending order");
        var ascMileageSortedCars = carsService.sort(Sort.MILEAGE, false);
        ascMileageSortedCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 2 - ALL CARS WITH MILLEAGE GREATER THAN ---------------------------------");
        System.out.println("Cars with milleage greater than 1600");
        var mileageGreaterThanCars = carsService.findAllWithMileageGreaterThan(1600);
        mileageGreaterThanCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 3 - CARS COUNTED BY COLOR IN DESCENDING ORDER ---------------------------------");
        var descCountByColorCars = carsService.descCountByColor();
        descCountByColorCars.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 4 - MOST EXPENSIVE CAR FOR EACH MODEL ---------------------------------");
        System.out.println("1. METHOD");
        var descMostExpensiveCarModel = carsService.descFindMostExpensiveCarForEachModel();
        descMostExpensiveCarModel.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("------------------------------------------------------------------");
        System.out.println("2. METHOD");
        var descMostExpensiveCarModel2 = carsService.descFindMostExpensiveCarForEachModel2();
        descMostExpensiveCarModel2.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 5 - CAR STATISTICS ---------------------------------");
        carsService.carStatistics();

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 6 - MOST EXPENSIVE CAR/S ---------------------------------");
        var mostExpensiveCars = carsService.mostExpensiveCars();
        mostExpensiveCars.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 7 - CARS COMPONENTS SORTED ALPHABETICALLY---------------------------------");
        var sortedCarsComponents = carsService.sortCarComponents();
        sortedCarsComponents.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 8 - GROUP BY COMPONENTS IN DESCENDING ORDER---------------------------------");
        var descGroupByCarComponents = carsService.descGroupByComponents();
        descGroupByCarComponents.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------------------------- 9 - CARS IN PRICE RANGE SORTED ALPHABETICALLY---------------------------------");
        int a = 100;
        int b = 300;
        var sortedCarsInPriceRange = carsService.findCarsInPriceRange(a, b);
        System.out.println("Cars in price range <" + a + ", " + b + ">, sorted alphabetically");
        sortedCarsInPriceRange.forEach(System.out::println);
    }
}

