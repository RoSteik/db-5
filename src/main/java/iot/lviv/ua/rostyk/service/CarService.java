/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.service
 * Interface: CarService
 */

package iot.lviv.ua.rostyk.service;

import iot.lviv.ua.rostyk.domain.Car;

import java.util.List;

public interface CarService extends GeneralService<Car, Integer> {
    List<Car> findCarByBrand(String brand);
    void createTablesWithCursor();
}
