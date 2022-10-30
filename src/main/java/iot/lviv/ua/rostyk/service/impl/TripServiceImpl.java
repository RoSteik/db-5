/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.service.impl
 * Class: TripServiceImpl
 */

package iot.lviv.ua.rostyk.service.impl;

import iot.lviv.ua.rostyk.domain.Driver;
import iot.lviv.ua.rostyk.domain.Trip;
import iot.lviv.ua.rostyk.domain.User;
import iot.lviv.ua.rostyk.exception.CarNotFoundException;
import iot.lviv.ua.rostyk.exception.DriverNotFoundException;
import iot.lviv.ua.rostyk.exception.TripNotFoundException;
import iot.lviv.ua.rostyk.repository.DriverRepository;
import iot.lviv.ua.rostyk.repository.TripRepository;
import iot.lviv.ua.rostyk.repository.UserRepository;
import iot.lviv.ua.rostyk.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip findById(Integer id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
    }

    @Override
    public Trip create(Trip trip) {
        tripRepository.save(trip);
        return trip;
    }

    @Override
    public void update(Integer id, Trip uTrip) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
        //update
        trip.setStartPoint(uTrip.getStartPoint());
        trip.setEndPoint(uTrip.getEndPoint());

        tripRepository.save(trip);
    }

    @Override
    public void delete(Integer id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
        tripRepository.delete(trip);
    }


}
