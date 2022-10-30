/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.controller
 * Class: TripController
 */

package iot.lviv.ua.rostyk.controller;

import iot.lviv.ua.rostyk.domain.Trip;
import iot.lviv.ua.rostyk.dto.TripDto;
import iot.lviv.ua.rostyk.dto.assembler.TripDtoAssembler;
import iot.lviv.ua.rostyk.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private TripDtoAssembler tripDtoAssembler;


    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TripDto>> getAllTrips() {
        List<Trip> trips = tripService.findAll();
        CollectionModel<TripDto> tripDtos = tripDtoAssembler.toCollectionModel(trips);
        return new ResponseEntity<>(tripDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{tripId}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Integer tripId) {
        Trip trip = tripService.findById(tripId);
        TripDto tripDto = tripDtoAssembler.toModel(trip);
        return new ResponseEntity<>(tripDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TripDto> addTrip(@RequestBody Trip trip) {
        Trip newTrip = tripService.create(trip);
        TripDto tripDto = tripDtoAssembler.toModel(newTrip);
        return new ResponseEntity<>(tripDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{tripId}")
    public ResponseEntity<?> updateTrip(@RequestBody Trip uTrip, @PathVariable Integer tripId) {
        tripService.update(tripId, uTrip);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tripId}")
    public ResponseEntity<?> deleteTrip(@PathVariable Integer tripId) {
        tripService.delete(tripId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
