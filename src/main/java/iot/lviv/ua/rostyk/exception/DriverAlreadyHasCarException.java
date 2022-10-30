/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.exception
 * Class: DriverAlreadyHasCarException
 */

package iot.lviv.ua.rostyk.exception;

public class DriverAlreadyHasCarException extends RuntimeException {
    public DriverAlreadyHasCarException(Integer carId, Integer driverId){
        super("'driver' with id=" + driverId +  " already have 'car' with id=" + carId);
    }
}
