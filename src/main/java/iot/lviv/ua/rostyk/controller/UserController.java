/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.controller
 * Class: UserController
 */

package iot.lviv.ua.rostyk.controller;


import iot.lviv.ua.rostyk.domain.User;
import iot.lviv.ua.rostyk.dto.UserDto;
import iot.lviv.ua.rostyk.dto.assembler.UserDtoAssembler;
import iot.lviv.ua.rostyk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoAssembler userDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        CollectionModel<UserDto> userDtos = userDtoAssembler.toCollectionModel(users);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        UserDto userDto = userDtoAssembler.toModel(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDto> addUser(@RequestBody User user) {
        User newUser = userService.create(user);
        UserDto userDto = userDtoAssembler.toModel(newUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User uUser, @PathVariable Integer userId) {
        userService.update(userId, uUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/procedure_insert")
    public ResponseEntity<UserDto> addUserWithProcedure(@RequestBody User user) {
        User newUser = userService.addUserWithProcedure(user.getName(), user.getRating());
        UserDto userDto = userDtoAssembler.toModel(newUser);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
