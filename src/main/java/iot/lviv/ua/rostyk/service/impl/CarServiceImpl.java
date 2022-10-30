/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.service.impl
 * Class: CarServiceImpl
 */

package iot.lviv.ua.rostyk.service.impl;

import iot.lviv.ua.rostyk.domain.Car;
import iot.lviv.ua.rostyk.exception.CarNotFoundException;
import iot.lviv.ua.rostyk.repository.CarRepository;
import iot.lviv.ua.rostyk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> findCarByBrand(String brand) {
        return carRepository.findCarByBrand(brand);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @Transactional
    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }

    @Transactional
    public void update(Integer id, Car uCar) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        //update
        car.setBrand(uCar.getBrand());
        car.setClas(uCar.getClas());

        carRepository.save(car);
    }

    @Transactional
    public void delete(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
    }
}
