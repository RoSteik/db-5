/**
 * Created by RoSteik (Telegram: @RoSteik)
 * Project name: rostyk
 * Package name: iot.lviv.ua.rostyk.service
 * Interface: UserService
 */

package iot.lviv.ua.rostyk.service;

import iot.lviv.ua.rostyk.domain.User;

public interface UserService extends GeneralService<User, Integer> {
    User addUserWithProcedure(String name, Integer rating);
}
