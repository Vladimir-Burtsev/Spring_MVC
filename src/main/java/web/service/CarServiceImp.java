package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImp implements CarService {
    private static int carId;
    private List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car(++carId, "car1", 1));
        cars.add(new Car(++carId, "car2", 2));
        cars.add(new Car(++carId, "car3", 3));
        cars.add(new Car(++carId, "car4", 4));
        cars.add(new Car(++carId, "car5", 5));
    }

    @Override
    public List<Car> showCars(int value) {
        return cars.stream().limit(value).collect(Collectors.toList());
    }
}