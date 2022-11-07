/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.controller
 * Class: DriverController
 */

package iot.lviv.ua.rostyk.controller;

import iot.lviv.ua.rostyk.domain.Car;
import iot.lviv.ua.rostyk.domain.Driver;
import iot.lviv.ua.rostyk.dto.CarDto;
import iot.lviv.ua.rostyk.dto.DriverDto;
import iot.lviv.ua.rostyk.dto.assembler.CarDtoAssembler;
import iot.lviv.ua.rostyk.dto.assembler.DriverDtoAssembler;
import iot.lviv.ua.rostyk.service.DriverService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverDtoAssembler driverDtoAssembler;
    @Autowired
    private CarDtoAssembler carDtoAssembler;

    @GetMapping(value = "/average_rating")
    public ResponseEntity<Integer> getAverageRating() {
        Integer avgRating = driverService.getAverageRating();
        return new ResponseEntity<>(avgRating, HttpStatus.OK);
    }

    @GetMapping(value = "/{driverId}/cars")
    public ResponseEntity<CollectionModel<CarDto>> getAllCarsForDriver(@PathVariable Integer driverId) {
        List<Car> cars = driverService.findCarsByDriverId(driverId);
        Link selfLink = linkTo(methodOn(DriverController.class).getAllCarsForDriver(driverId)).withSelfRel();
        CollectionModel<CarDto> carDtos = carDtoAssembler.toCollectionModel(cars, selfLink);
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{driverId}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable Integer driverId) {
        Driver driver = driverService.findById(driverId);
        DriverDto driverDto = driverDtoAssembler.toModel(driver);
        return new ResponseEntity<>(driverDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<DriverDto>> getAllDrivers() {
        List<Driver> drivers = driverService.findAll();
        CollectionModel<DriverDto> driverDtos = driverDtoAssembler.toCollectionModel(drivers);
        return new ResponseEntity<>(driverDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DriverDto> addDriver(@RequestBody Driver driver) {
        Driver newDriver = driverService.create(driver);
        DriverDto driverDto = driverDtoAssembler.toModel(newDriver);
        return new ResponseEntity<>(driverDto, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(value = "/relationship")
    public ResponseEntity<?> addDriverHasCarRelationship(@RequestBody JSONObject jsonObject) {
        driverService.addDriverHasCarRelationship(jsonObject.getAsString("driver_name"), jsonObject.getAsString("car_brand"));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(value = "/{driverId}")
    public ResponseEntity<?> updateDriver(@RequestBody Driver uDriver, @PathVariable Integer driverId) {
        driverService.update(driverId, uDriver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer driverId) {
        driverService.delete(driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
