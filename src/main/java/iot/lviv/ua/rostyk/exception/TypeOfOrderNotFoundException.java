/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.exception
 * Class: TypeOfOrderNotFoundException
 */

package iot.lviv.ua.rostyk.exception;

public class TypeOfOrderNotFoundException extends RuntimeException {
    public TypeOfOrderNotFoundException(Integer id) {
        super("Can`t find type od order with id: " + id);
    }
}