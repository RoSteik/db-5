/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.exception
 * Class: DriverHasNoCarException
 */

package iot.lviv.ua.rostyk.exception;

public class DriverHasNoCarException extends RuntimeException {
    public DriverHasNoCarException(Integer carId, Integer driverId){
        super("'driver' with id=" + driverId +  " doesn't have 'car' with id=" + carId);
    }
}
