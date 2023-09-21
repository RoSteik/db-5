/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.service.impl
 * Class: DriverServiceImpl
 */

package iot.lviv.ua.rostyk.service.impl;

import iot.lviv.ua.rostyk.domain.Car;
import iot.lviv.ua.rostyk.domain.Driver;
import iot.lviv.ua.rostyk.exception.DriverNotFoundException;
import iot.lviv.ua.rostyk.repository.DriverRepository;
import iot.lviv.ua.rostyk.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Override
    public Integer getAverageRating() {
        return driverRepository.getAverageRating();
    }

    @Override
    public void addDriverHasCarRelationship(String driverName, String carBrand) {
        driverRepository.addDriverHasCarRelationship(driverName, carBrand);
    }

    @Override
    public List<Car> findCarsByDriverId(Integer driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException(driverId));
        return new ArrayList<>(driver.getCars());
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver findById(Integer id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
    }

    @Override
    public Driver create(Driver driver) {
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public void update(Integer id, Driver uDriver) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
        driver.setName(uDriver.getName());
        driver.setRating(uDriver.getRating());
        driver.setCompletedOrders(uDriver.getCompletedOrders());
        driver.setIsVacant(uDriver.getIsVacant());

        driverRepository.save(driver);
    }

    @Override
    public void delete(Integer id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
        driverRepository.delete(driver);
    }
}
