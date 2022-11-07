/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.controller
 * Class: CarController
 */

package iot.lviv.ua.rostyk.controller;

import iot.lviv.ua.rostyk.domain.Car;
import iot.lviv.ua.rostyk.dto.CarDto;
import iot.lviv.ua.rostyk.dto.assembler.CarDtoAssembler;
import iot.lviv.ua.rostyk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private CarDtoAssembler carDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CarDto>> getAllCars() {
        List<Car> cars = carService.findAll();
        CollectionModel<CarDto> carDtos = carDtoAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDto> getCar(@PathVariable Integer carId) {
        Car car = carService.findById(carId);
        CarDto carDto = carDtoAssembler.toModel(car);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping(value = "/brand/{brand}")
    public ResponseEntity<CollectionModel<CarDto>> getCarByBrand(@PathVariable String brand) {
        List<Car> cars = carService.findCarByBrand(brand);
        CollectionModel<CarDto> carDtos = carDtoAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CarDto> addCar(@RequestBody Car car) {
        Car newCar = carService.create(car);
        CarDto carDto = carDtoAssembler.toModel(newCar);
        return new ResponseEntity<>(carDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/cursor")
    public ResponseEntity<?> createTablesWithCursor() {
        carService.createTablesWithCursor();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{carId}")
    public ResponseEntity<?> updateCar(@RequestBody Car uCar, @PathVariable Integer carId) {
        carService.update(carId, uCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer carId) {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
