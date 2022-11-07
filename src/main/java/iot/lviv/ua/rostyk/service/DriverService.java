/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.service
 * Interface: DriverService
 */

package iot.lviv.ua.rostyk.service;

import iot.lviv.ua.rostyk.domain.Car;
import iot.lviv.ua.rostyk.domain.Driver;

import java.util.List;

public interface DriverService extends GeneralService<Driver, Integer> {
    List<Car> findCarsByDriverId(Integer driverId);
    Integer getAverageRating();
    void addDriverHasCarRelationship(String driverName, String carBrand);
}
