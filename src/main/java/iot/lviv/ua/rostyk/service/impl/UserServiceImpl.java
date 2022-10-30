/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.service.impl
 * Class: UserServiceImpl
 */

package iot.lviv.ua.rostyk.service.impl;


import iot.lviv.ua.rostyk.domain.User;
import iot.lviv.ua.rostyk.exception.UserNotFoundException;
import iot.lviv.ua.rostyk.repository.UserRepository;
import iot.lviv.ua.rostyk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void update(Integer id, User uUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        //update
        user.setName(uUser.getName());
        user.setRating(uUser.getRating());

        userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
