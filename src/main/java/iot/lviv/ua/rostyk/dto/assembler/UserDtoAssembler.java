/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: rostyk
 * Package: iot.lviv.ua.rostyk.dto.assembler
 * Class: UserDtoAssembler
 */

package iot.lviv.ua.rostyk.dto.assembler;

import iot.lviv.ua.rostyk.controller.UserController;
import iot.lviv.ua.rostyk.domain.User;
import iot.lviv.ua.rostyk.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserDtoAssembler implements RepresentationModelAssembler<User, UserDto> {

    @Override
    public UserDto toModel(User entity) {
        UserDto userDto = UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .rating(entity.getRating())
                .build();
        Link selfLink = linkTo(methodOn(UserController.class).getUser(Math.toIntExact(userDto.getId()))).withSelfRel();
        userDto.add(selfLink);
        return userDto;
    }

    @Override
    public CollectionModel<UserDto> toCollectionModel(Iterable<? extends User> entities) {
        CollectionModel<UserDto> userDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        userDtos.add(selfLink);
        return userDtos;
    }
}
