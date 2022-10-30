/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.exception
 * Class: CarNotFoundException
 */

package iot.lviv.ua.rostyk.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Integer id) {
        super("Could not find 'car' with id = " + id);
    }

}
