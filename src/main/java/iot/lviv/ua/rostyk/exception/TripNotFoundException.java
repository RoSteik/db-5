/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.exception
 * Class: TripNotFoundException
 */

package iot.lviv.ua.rostyk.exception;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(Integer id) {
        super("Could not find 'trip' with id = " + id);
    }
}
