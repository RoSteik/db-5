/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.exception
 * Class: DriverNotFoundException
 */

package iot.lviv.ua.rostyk.exception;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(Integer id) {
        super("Could not find 'driver' with id = " + id);
    }
}
