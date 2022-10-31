/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.service
 * Interface: TripService
 */

package iot.lviv.ua.rostyk.service;
import iot.lviv.ua.rostyk.domain.Trip;

import java.util.List;

public interface TripService extends GeneralService<Trip, Integer> {
    List<Trip> findTripsByDriverId(Integer driverId);
}
